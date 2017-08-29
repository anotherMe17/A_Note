package com.anotherme17.anothernote.entity;

import java.util.Date;
import java.util.List;

/**
 * 计划生成行程的规则
 */
public class PlanRule {

    private Date startTime;

    private Date endTime;

    /**
     * 包含时间的正则表达式
     */
    private String includeRegular;

    /**
     * 包含的星期
     */
    private List<Integer> includeDayInWeek;

    /**
     * 排除时间的正则表达式
     */
    private String excludeRegular;

    /**
     * 排除的星期
     */
    private List<Integer> excludeDayInWeek;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getIncludeRegular() {
        return includeRegular;
    }

    public void setIncludeRegular(String includeRegular) {
        this.includeRegular = includeRegular;
    }

    public List<Integer> getIncludeDayInWeek() {
        return includeDayInWeek;
    }

    public void setIncludeDayInWeek(List<Integer> includeDayInWeek) {
        this.includeDayInWeek = includeDayInWeek;
    }

    public String getExcludeRegular() {
        return excludeRegular;
    }

    public void setExcludeRegular(String excludeRegular) {
        this.excludeRegular = excludeRegular;
    }

    public List<Integer> getExcludeDayInWeek() {
        return excludeDayInWeek;
    }

    public void setExcludeDayInWeek(List<Integer> excludeDayInWeek) {
        this.excludeDayInWeek = excludeDayInWeek;
    }
}
