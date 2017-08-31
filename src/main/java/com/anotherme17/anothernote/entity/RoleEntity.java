package com.anotherme17.anothernote.entity;

import java.util.Set;

/**
 * 角色
 */
public class RoleEntity {

    private String id;

    private String role;

    private String description;

    private boolean available;

    private Set<PermissionEntity> permissions;

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", permissions=" + permissions +
                '}';
    }
}
