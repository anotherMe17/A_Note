package com.anotherme17.anothernote.service.impl;

import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.mapper.UserMapper;
import com.anotherme17.anothernote.result.BasePageResult;
import com.anotherme17.anothernote.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * UserServiceImpl
 */
@Service
@Transactional
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
        /*分页处理*/
        PageHelper.startPage(page, rows);

        List<UserEntity> users = mUserMapper.getUserList();

        PageInfo<UserEntity> pageInfo = new PageInfo<>(users);

        return new BasePageResult<>(1, "ok", users, rows, page, pageInfo.getTotal());
    }

    @Override
    public UserEntity getUserByID(String id) {
        return mUserMapper.getUserByID(id);
    }

    @Override
    public void updateUser(UserEntity user) {
        mUserMapper.updateUser(user);
    }

    @Override
    public void updateLastLoginTime(String id, Date loginTime) {
        mUserMapper.updateLastLoginTime(id, loginTime);
    }

    @Override
    public List<UserEntity> authentication(String username, String password) {
        return mUserMapper.authentication(username, password);
    }
}
