package com.anotherme17.anothernote.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 行程计划
 */
@ApiModel(value = "StrokePlan", description = "行程计划对象")
public class StrokePlanEntity {

    public static final int ENABLE_NO = 0;
    public static final int ENABLE_YES = 1;
    public static final int ENABLE_NOT_NOW = 2;
    public static final int ENABLE_FINISH = 3;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "所属用户")
    private String forUserID;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "上一个计划")
    private String afterPlanID;

    @ApiModelProperty(value = "上一次生成行程的时间")
    private Date lastGenerateTime;

    @ApiModelProperty(value = "生成行程的规则")
    private String planRule;

    @ApiModelProperty(value = "是否启用")
    private int enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getForUserID() {
        return forUserID;
    }

    public void setForUserID(String forUserID) {
        this.forUserID = forUserID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAfterPlanID() {
        return afterPlanID;
    }

    public void setAfterPlanID(String afterPlanID) {
        this.afterPlanID = afterPlanID;
    }

    public Date getLastGenerateTime() {
        return lastGenerateTime;
    }

    public void setLastGenerateTime(Date lastGenerateTime) {
        this.lastGenerateTime = lastGenerateTime;
    }

    public String getPlanRule() {
        return planRule;
    }

    public void setPlanRule(String planRule) {
        this.planRule = planRule;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
