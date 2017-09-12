package com.anotherme17.anothernote.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 行程表
 */
@Accessors(chain = true)
@Data
@ApiModel(value = "Stroke", description = "行程对象")
@RequiredArgsConstructor(staticName = "of")
public class StrokeEntity {

    /**
     * 处理中
     */
    public static final int STATE_PROCESSING = 0;
    /**
     * 已完成
     */
    public static final int STATE_FINISH = 1;

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

    public StrokeEntity(StrokePlanEntity strokePlan, Date startTime, Date endTime) {
        this.title = strokePlan.getTitle();
        this.content = strokePlan.getContent();
        this.strokePlanID = strokePlan.getId();
        this.forUserID = strokePlan.getForUserID();
        this.createTime = new Date();
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = STATE_PROCESSING;
    }
}
