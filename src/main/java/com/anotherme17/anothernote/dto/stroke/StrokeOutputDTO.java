package com.anotherme17.anothernote.dto.stroke;

import com.anotherme17.anothernote.entity.StrokeEntity;
import com.google.common.base.Converter;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 */
@Data
public class StrokeOutputDTO {
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "计划ID")
    private String strokePlanID;

    @ApiModelProperty(value = "所属用户")
    private String forUserID;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "状态")
    private int state;

    public StrokeEntity convertToStroke() {
        StrokeOutputDTOConvert strokeOutputDTOConvert = new StrokeOutputDTOConvert();
        return strokeOutputDTOConvert.convert(this);
    }

    public StrokeOutputDTO convertFor(StrokeEntity strokeEntity) {
        StrokeOutputDTOConvert strokeOutputDTOConvert = new StrokeOutputDTOConvert();
        return strokeOutputDTOConvert.reverse().convert(strokeEntity);
    }

    private static class StrokeOutputDTOConvert extends Converter<StrokeOutputDTO, StrokeEntity> {

        @Override
        protected StrokeEntity doForward(StrokeOutputDTO strokeOutputDTO) {
            throw new AssertionError("不支持正向转化方法!");
        }

        @Override
        protected StrokeOutputDTO doBackward(StrokeEntity strokeEntity) {
            StrokeOutputDTO strokeOutputDTO = new StrokeOutputDTO();
            BeanUtils.copyProperties(strokeEntity, strokeOutputDTO);
            return strokeOutputDTO;
        }
    }
}
