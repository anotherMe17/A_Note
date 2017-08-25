package com.anotherme17.anothernote.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 */
@ApiModel(value = "Result", description = "返回结果")
public class BaseResult<T> {

    @ApiModelProperty(value = "代码")
    private int code;

    @ApiModelProperty(value = "消息内容")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T data;

    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
