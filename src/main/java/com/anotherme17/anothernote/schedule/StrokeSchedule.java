package com.anotherme17.anothernote.schedule;

import com.anotherme17.anothernote.service.StrokePlanService;
import com.anotherme17.anothernote.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * 行程 定时任务
 */
@Component
public class StrokeSchedule {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StrokePlanService mStrokePlanService;

    /**
     * 通过行程计划生成行程
     */
    @Scheduled(cron = "0 40 14 ? * *")
    public void generateStrokePlan() {
        /*生成一天之后的行程*/
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        logger.info(String.format("开始生成 %s 的行程", DateUtils.formatDay(calendar.getTime())));
        int strokeSize = mStrokePlanService.generateStrokePlanBeforeDate(calendar.getTime());
        logger.info(String.format("%s 的行程 生成结束 共产生 %s 条记录", DateUtils.formatDay(calendar.getTime()), strokeSize));
    }
}
