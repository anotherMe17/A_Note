package com.anotherme17.anothernote.config.redis.token;

import com.anotherme17.anothernote.utils.MyDES;

/**
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 */
public class TokenModel {

    public static String url = "anotherme17";

    //用户id
    private String userId;

    //随机生成的uuid
    private String token;


    public TokenModel(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return MyDES.encryptBasedDes(userId + "_" + token + "_" + url);
    }
}
