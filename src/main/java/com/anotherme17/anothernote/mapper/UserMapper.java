package com.anotherme17.anothernote.mapper;

import com.anotherme17.anothernote.entity.UserEntity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserMapper
 */
@Mapper
@Repository
@Transactional
public interface UserMapper {

    /**
     * 插入用户,自动生成主键
     */
    void insertUserAutoKey(UserEntity user);

    /**
     * 删除用户
     */
    void deleteUser(String id);

    /**
     * 更新用户
     */
    void updateUser(UserEntity user);

    /**
     * 通过ID获取用户信息
     */
    UserEntity getUserByID(String id);

    /**
     * 获取所有的用户数量
     */
    Integer getUserCount();

    /**
     * 获取用户列表-分页
     */
    List<UserEntity> getUserList();

}
