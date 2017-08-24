package com.anotherme17.anothernote;

import com.anotherme17.anothernote.entity.UserEntity;
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

    @Test
    @Transactional
    public void contextLoads() {
        UserEntity user = new UserEntity("123", "456", "789", "11");

        mUserMapper.insertUserAutoKey(user);
    }

}
