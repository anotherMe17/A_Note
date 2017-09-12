package com.anotherme17.anothernote.result;

import com.anotherme17.anothernote.config.code.ResultCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 */
@Builder
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@ApiModel(value = "Result", description = "返回结果")
public class BaseResult<T> {

    @ApiModelProperty(value = "代码")
    private int code;

    @ApiModelProperty(value = "消息内容")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T data;

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> BaseResult<T> ofResult(ResultCode resultCode, T data) {
        return new BaseResult<T>(resultCode.getCode(), resultCode.getMsg(), data);
    }

    public static <T> BaseResult<T> ofResult(ResultCode resultCode) {
        return new BaseResult<T>(resultCode.getCode(), resultCode.getMsg());
    }
}
