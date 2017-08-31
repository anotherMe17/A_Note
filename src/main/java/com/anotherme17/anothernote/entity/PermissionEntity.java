package com.anotherme17.anothernote.entity;

/**
 * 权限
 */
public class PermissionEntity {

    private String id;

    private String name;

    private String permission;

    private boolean available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", permission='" + permission + '\'' +
                ", available=" + available +
                '}';
    }
}
