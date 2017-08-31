package com.anotherme17.anothernote.mapper;

import com.anotherme17.anothernote.entity.RoleEntity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Administrator on 2017/8/31.
 */
@Mapper
@Repository
public interface RoleMapper {

    Set<RoleEntity> getRoleByUserID(String id);
}
