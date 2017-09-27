package com.anotherme17.anothernote.service;

import com.anotherme17.anothernote.entity.RoleEntity;

import java.util.Set;

/**
 * RoleService
 */
public interface RoleService {

    Set<RoleEntity> getRoleByUserID(String id);
}
