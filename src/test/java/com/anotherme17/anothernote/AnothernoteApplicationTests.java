package com.anotherme17.anothernote;

import com.anotherme17.anothernote.mapper.PermissionMapper;
import com.anotherme17.anothernote.mapper.UserMapper;
import com.anotherme17.anothernote.service.RoleService;
import com.anotherme17.anothernote.service.impl.MailService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnothernoteApplicationTests {

    @Autowired
    UserMapper mUserMapper;

    @Autowired
    RoleService mRoleService;

    @Autowired
    PermissionMapper mPermissionMapper;

    @Autowired
    private MailService mailService;

    @Test
    @Transactional
    public void contextLoads() {

        mailService.sendSimpleMail("411867400@qq.com", "主题：简单邮件", "测试邮件内容");

    }

    @Test
    @Transactional
    public void ehcacheTest() {
        System.out.println(mRoleService.getRoleByUserID("4979bd0b896a11e780b84ccc6a6d905e"));
        System.out.println(mRoleService.getRoleByUserID("4979bd0b896a11e780b84ccc6a6d905e"));
    }

}
