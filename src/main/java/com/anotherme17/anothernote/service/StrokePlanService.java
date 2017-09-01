package com.anotherme17.anothernote.service;

import com.anotherme17.anothernote.entity.StrokePlanEntity;

import java.util.Date;

/**
 * StrokePlanService
 */
public interface StrokePlanService {

    /**
     * 插入行程计划
     */
    void insertStrokePlan(StrokePlanEntity strokePlan);

    int generateStrokePlanBeforeDate(Date date);
}
