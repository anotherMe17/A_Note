package com.anotherme17.anothernote.mapper;

import com.anotherme17.anothernote.entity.PermissionEntity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * PermissionMapper
 */
@Mapper
@Repository
public interface PermissionMapper {

    Set<PermissionEntity> getPermissionByUserID(String id);
}
