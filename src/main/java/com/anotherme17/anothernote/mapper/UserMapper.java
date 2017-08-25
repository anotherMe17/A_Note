package com.anotherme17.anothernote.mapper;

import com.anotherme17.anothernote.entity.UserEntity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserMapper
 */
@Mapper
@Repository
@Transactional
public interface UserMapper {

    public void insertUserAutoKey(UserEntity user);

}
