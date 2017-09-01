package com.anotherme17.anothernote.schedule;

import com.anotherme17.anothernote.entity.StrokeEntity;
import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.service.StrokeService;
import com.anotherme17.anothernote.service.UserService;
import com.anotherme17.anothernote.service.impl.MailService;
import com.anotherme17.anothernote.utils.DateUtils;
import com.anotherme17.anothernote.utils.TextUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * EmailSchedule
 */
@Component
public class EmailSchedule {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService mUserService;

    @Autowired
    private StrokeService mStrokeService;

    @Autowired
    private MailService mMailService;

    @Scheduled(cron = "0 32 16 ? * *")
    //@Scheduled(cron = "0 40 14 ? * MON-FRI")
    public void emailStrokeToUser() {
        /*获取当天行程*/
        logger.info("开始发送行程邮件");
        Date nowDate = new Date();
        List<UserEntity> users = mUserService.getUserStrokeInDay(DateUtils.getDayBegin(nowDate), DateUtils.getDayEnd(nowDate));

        if (users == null) {
            logger.info("目标用户数: " + 0);
            logger.info("无邮件发送");
            return;
        } else {
            logger.info("目标用户数: " + users.size());
        }

        int count = 0;

        for (UserEntity user : users) {
            if (TextUtils.isEmpty(user.getEmail()))
                continue;

            if (user.getStrokes() == null)
                continue;

            String subject = user.getUsername() + " " + DateUtils.formatDay(nowDate) + " 行程安排";
            StringBuilder content = new StringBuilder();
            for (StrokeEntity stroke : user.getStrokes()) {
                content.append(stroke.getTitle()).append("   ").append(stroke.getContent()).append("\n");
            }

            try {
                mMailService.sendSimpleMail(user.getEmail(), subject, content.toString());
                logger.info("发送邮件至用户: " + user.getUsername() + " 邮箱: " + user.getEmail());
                count++;
            } catch (Exception e) {
                logger.info("发送邮件至用户: " + user.getUsername() + " 邮箱: " + user.getEmail() + " 时发生错误!!!");
                logger.info(e.toString());
            }
        }
        logger.info("邮件发送结束 共发送邮件数 " + count);
    }
}
