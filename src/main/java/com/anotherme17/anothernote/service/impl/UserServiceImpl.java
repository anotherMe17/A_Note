package com.anotherme17.anothernote.service.impl;

import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.mapper.UserMapper;
import com.anotherme17.anothernote.result.BasePageResult;
import com.anotherme17.anothernote.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteUser(String id) {
        mUserMapper.deleteUser(id);
    }

    @Override
    public BasePageResult<UserEntity> getUserList(int page, int rows) {
        int start = page * rows;
        int end = start + rows;
        Integer count = mUserMapper.getUserCount();
        List<UserEntity> users = mUserMapper.getUserList(start, end);
        return new BasePageResult<>(1, "ok", users, rows, page, count == null ? 0 : count);
    }

    @Override
    public UserEntity getUserByID(String id) {
        return mUserMapper.getUserByID(id);
    }

    @Override
    public void updateUser(UserEntity user) {
        mUserMapper.updateUser(user);
    }
}
