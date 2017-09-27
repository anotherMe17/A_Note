package com.anotherme17.anothernote.service.impl;

import com.anotherme17.anothernote.entity.PermissionEntity;
import com.anotherme17.anothernote.mapper.PermissionMapper;
import com.anotherme17.anothernote.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.Set;

/**
 * PermissionServiceImpl
 */
public class PermissionServiceImpl implements PermissionService {

    public static final String CACHE_KEY = "'permission_'";

    public static final String CACHE_NAME = "roleAndPermissionCache";

    @Autowired
    private PermissionMapper mPermissionMapper;

    @Override
    @Cacheable(value = CACHE_NAME, key = CACHE_KEY + "+#id")
    public Set<PermissionEntity> getPermissionByUserID(String id) {
        return mPermissionMapper.getPermissionByUserID(id);
    }
}
