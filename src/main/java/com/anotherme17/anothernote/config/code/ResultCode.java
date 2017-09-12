package com.anotherme17.anothernote.config.code;

/**
 * 错误代码
 */
public enum ResultCode {
    OK(100, "ok"),
    DELETE_REFUSE(301, "不能删除该条记录"),
    UNKNOWN_RECORD(404, "找不到该条记录"),
    ERROR_UNKNOW(504, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
