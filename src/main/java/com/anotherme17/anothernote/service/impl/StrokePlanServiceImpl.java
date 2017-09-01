package com.anotherme17.anothernote.service.impl;

import com.alibaba.fastjson.JSON;
import com.anotherme17.anothernote.entity.PlanRule;
import com.anotherme17.anothernote.entity.StrokeEntity;
import com.anotherme17.anothernote.entity.StrokePlanEntity;
import com.anotherme17.anothernote.mapper.StrokeMapper;
import com.anotherme17.anothernote.mapper.StrokePlanMapper;
import com.anotherme17.anothernote.service.StrokePlanService;
import com.anotherme17.anothernote.utils.PlanRuleUtils;
import com.anotherme17.anothernote.utils.TextUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * StrokePlanServiceImpl
 */
@Service
@Transactional
public class StrokePlanServiceImpl implements StrokePlanService {

    @Autowired
    private StrokeMapper mStrokeMapper;

    @Autowired
    private StrokePlanMapper mStrokePlanMapper;

    @Override
    public void insertStrokePlan(StrokePlanEntity strokePlan) {
        /*判断是否需要生成行程*/
        /*
        * 如果左后一次计算时间为空,或小于当前时间.表示要进行计算.
        * LastGenerateTime 也可以表示开始计算的时间
        * */
        if (strokePlan.getLastGenerateTime() == null
                || strokePlan.getLastGenerateTime().before(new Date())) {

            if (TextUtils.isEmpty(strokePlan.getPlanRule()))
                throw new RuntimeException("规则不能为空");

            /*设置enable属性,判断上一个计划是否执行完成*/
            if (!TextUtils.isEmpty(strokePlan.getAfterPlanID())) {
                StrokePlanEntity beforeStrokePlan = mStrokePlanMapper.getStrokePlainByID(strokePlan.getAfterPlanID());
                if (beforeStrokePlan != null && beforeStrokePlan.getEnable() == StrokePlanEntity.ENABLE_FINISH) {
                    strokePlan.setEnable(StrokePlanEntity.ENABLE_YES);
                } else {
                    strokePlan.setEnable(StrokePlanEntity.ENABLE_NOT_NOW);
                }
            } else {
                strokePlan.setEnable(StrokePlanEntity.ENABLE_YES);
            }

            /*解析planRule转换成对象*/
            PlanRule rule = JSON.parseObject(strokePlan.getPlanRule(), PlanRule.class);

            Date nowDate = new Date();

            if (PlanRuleUtils.ifCreateStroke(rule, nowDate)) {
                 /*设置行程计划的最后生成时间*/
                strokePlan.setLastGenerateTime(nowDate);
                /*插入行程计划*/
                mStrokePlanMapper.insertStrokePlanAutoKey(strokePlan);

                /*生成行程*/
                StrokeEntity stroke = new StrokeEntity(strokePlan,
                        PlanRuleUtils.getStrokeTime(nowDate, rule.getStartTime()),
                        PlanRuleUtils.getStrokeTime(nowDate, rule.getEndTime()));
                mStrokeMapper.insertStrokeAutoKey(stroke);
            } else {
                mStrokePlanMapper.insertStrokePlanAutoKey(strokePlan);
            }
        } else {
            mStrokePlanMapper.insertStrokePlanAutoKey(strokePlan);
        }
    }

    @Override
    public int generateStrokePlanBeforeDate(Date date) {
        /*获取所有需要生成行程的计划*/
        int strokeSize = 0;
        List<StrokePlanEntity> strokePlans = mStrokePlanMapper.getStrokePlanBeforeDate(date);
        for (StrokePlanEntity strokePlan : strokePlans) {
            if (generateStrokePlan(strokePlan, date)) {
                strokeSize++;
            }
        }
        return strokeSize;
    }

    private boolean generateStrokePlan(StrokePlanEntity strokePlan, Date date) {
        boolean isCreate = false;
        if (strokePlan.getLastGenerateTime() == null
                || strokePlan.getLastGenerateTime().before(new Date())) {

            if (TextUtils.isEmpty(strokePlan.getPlanRule()))
                return false;

            if (strokePlan.getEnable() != StrokePlanEntity.ENABLE_YES)
                return false;

            /*解析planRule转换成对象*/
            PlanRule rule = JSON.parseObject(strokePlan.getPlanRule(), PlanRule.class);

            if (PlanRuleUtils.ifCreateStroke(rule, date)) {
                /*生成行程*/
                StrokeEntity stroke = new StrokeEntity(strokePlan,
                        PlanRuleUtils.getStrokeTime(date, rule.getStartTime()),
                        PlanRuleUtils.getStrokeTime(date, rule.getEndTime()));
                mStrokeMapper.insertStrokeAutoKey(stroke);
                isCreate = true;
            }
        }
        /*设置行程计划的最后生成时间*/
        strokePlan.setLastGenerateTime(date);
            /*更新行程计划*/
        mStrokePlanMapper.updateStrokePlan(strokePlan);
        return isCreate;
    }
}
