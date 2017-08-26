package com.anotherme17.anothernote.service;

import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.result.BasePageResult;

/**
 * UserService
 */
public interface UserService {

    /**
     * 添加用户
     */
    void insertUser(UserEntity user);

    /**
     * 删除用户
     */
    void deleteUser(String id);

    BasePageResult<UserEntity> getUserList(int page, int rows);

    /**
     * 通过ID获取用户详情
     */
    UserEntity getUserByID(String id);

    void updateUser(UserEntity user);
}
