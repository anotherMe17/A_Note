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

    BasePageResult<UserEntity> getUserList();
}
