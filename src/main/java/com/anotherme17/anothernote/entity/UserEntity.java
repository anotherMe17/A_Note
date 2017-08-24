package com.anotherme17.anothernote.entity;

/**
 * 用户表
 */
public class UserEntity {

    private String id="123";
    private String phone;
    private String username;
    private String password;
    private String email;

    public UserEntity() {
    }

    public UserEntity(String phone, String username, String password, String email) {
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.email = email;
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
}
