package com.anotherme17.anothernote.entity;


import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户表
 */
@ApiModel(value = "User", description = "用户对象")
public class UserEntity {

    public static final int STATE_UN_ACTIVE = 0;
    public static final int STATE_ACTIVE = 1;
    public static final int STATE_LOCKED = 2;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码", hidden = true)
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    private int state = STATE_UN_ACTIVE;

    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    public UserEntity(String phone, String username, String password, String email) {
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
