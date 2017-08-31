package com.anotherme17.anothernote;

import com.anotherme17.anothernote.mapper.PermissionMapper;
import com.anotherme17.anothernote.mapper.RoleMapper;
import com.anotherme17.anothernote.mapper.UserMapper;

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
    RoleMapper mRoleMapper;

    @Autowired
    PermissionMapper mPermissionMapper;

    @Test
    @Transactional
    public void contextLoads() {
       /* UserEntity user = new UserEntity("123", "456", "789", "11");

        mUserMapper.insertUserAutoKey(user);*/
        System.out.println("=================================================");
        System.out.println(mRoleMapper.getRoleByUserID("4979bd0b896a11e780b84ccc6a6d905e").toString());
        System.out.println("=================================================");

        System.out.println("=================================================");
        System.out.println(mPermissionMapper.getPermissionByUserID("4979bd0b896a11e780b84ccc6a6d905e").toString());
        System.out.println("=================================================");
    }

}
