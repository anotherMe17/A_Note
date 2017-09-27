package com.anotherme17.anothernote.service.impl;

import com.anotherme17.anothernote.entity.RoleEntity;
import com.anotherme17.anothernote.mapper.RoleMapper;
import com.anotherme17.anothernote.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * RoleServiceImpl
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    public static final String CACHE_KEY = "'role_'";

    public static final String CACHE_NAME = "roleAndPermissionCache";

    @Autowired
    private RoleMapper mRoleMapper;

    @Override
    @Cacheable(value = CACHE_NAME, key = CACHE_KEY + "+#id")
    public Set<RoleEntity> getRoleByUserID(String id) {
        return mRoleMapper.getRoleByUserID(id);
    }
}
