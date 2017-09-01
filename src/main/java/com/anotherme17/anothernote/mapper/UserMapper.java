package com.anotherme17.anothernote.mapper;

import com.anotherme17.anothernote.entity.UserEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * UserMapper
 */
@Mapper
@Repository
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
     * 更新登录时间
     */
    void updateLastLoginTime(@Param("id") String id, @Param("loginTime") Date loginTime);

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

    List<UserEntity> authentication(@Param("username") String username, @Param("password") String password);

    List<UserEntity> getUserStrokeInDay(@Param("left") Date left, @Param("right") Date right);

}
