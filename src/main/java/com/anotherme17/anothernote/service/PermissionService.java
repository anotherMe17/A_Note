package com.anotherme17.anothernote.service;

import com.anotherme17.anothernote.entity.PermissionEntity;

import java.util.Set;

/**
 * PermissionService
 */
public interface PermissionService {

    Set<PermissionEntity> getPermissionByUserID(String id);
}
