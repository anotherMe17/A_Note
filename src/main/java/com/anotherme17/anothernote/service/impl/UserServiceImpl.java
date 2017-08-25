package com.anotherme17.anothernote.service.impl;

import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.mapper.UserMapper;
import com.anotherme17.anothernote.result.BasePageResult;
import com.anotherme17.anothernote.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mUserMapper;


    @Override
    public void insertUser(UserEntity user) {
        mUserMapper.insertUserAutoKey(user);
    }

    @Override
    public BasePageResult<UserEntity> getUserList() {
        return null;
    }
}
