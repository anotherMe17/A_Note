package com.anotherme17.anothernote.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 无状态的Token
 */
public class StatelessToken implements AuthenticationToken {

    private String username;//用户身份即用户名
    private String clientDigest;//凭证即客户端传入的消息摘要

    public StatelessToken() {
    }

    public StatelessToken(String username, String clientDigest) {
        this.username = username;
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }
}
