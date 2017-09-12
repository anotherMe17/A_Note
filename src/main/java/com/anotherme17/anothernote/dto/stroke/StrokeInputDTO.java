package com.anotherme17.anothernote.dto.stroke;

import com.anotherme17.anothernote.entity.StrokeEntity;
import com.google.common.base.Converter;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.Future;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行程
 */
@Data
public class StrokeInputDTO {

    @ApiModelProperty(value = "标题")
    @NotBlank(groups = {CREATE.class}, message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "内容")
    @NotBlank(groups = {CREATE.class}, message = "内容不能为空")
    private String content;

    @ApiModelProperty(value = "计划ID")
    private String strokePlanID;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Future(groups = {CREATE.class}, message = "开始时间不能早于当前时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Future(groups = {CREATE.class}, message = "结束时间不能早于当前时间")
    private Date endTime;

    public interface CREATE {
    }

    public interface UPDATE {
    }


    public StrokeEntity convertToStroke() {
        StrokeInputDTOConvert strokeInputDTO = new StrokeInputDTOConvert();
        return strokeInputDTO.convert(this);
    }

    public StrokeInputDTO convertFor(StrokeEntity strokeEntity) {
        StrokeInputDTOConvert strokeInputDTO = new StrokeInputDTOConvert();
        return strokeInputDTO.reverse().convert(strokeEntity);
    }

    private static class StrokeInputDTOConvert extends Converter<StrokeInputDTO, StrokeEntity> {

        @Override
        protected StrokeEntity doForward(StrokeInputDTO strokeInputDTO) {
            StrokeEntity strokeEntity = StrokeEntity.of();
            BeanUtils.copyProperties(strokeInputDTO, strokeEntity);
            return strokeEntity;
        }

        @Override
        protected StrokeInputDTO doBackward(StrokeEntity strokeEntity) {
            throw new AssertionError("不支持逆向转化方法!");
        }
    }
}
