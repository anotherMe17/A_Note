package com.anotherme17.anothernote.utils;

import com.anotherme17.anothernote.entity.PlanRule;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * PlanRuleUtils
 */
public class PlanRuleUtils {

    /**
     * 判断是否可以生成计划
     */
    public static boolean ifCreateStroke(PlanRule rule, Date time) {
        if (rule == null) return false;

        String ymdw = DateUtils.getYMDW(time);
        /*先判断排除的情况*/
        if (!TextUtils.isEmpty(rule.getExcludeRegular())
                && !Pattern.matches(rule.getExcludeRegular(), ymdw)) {
            /*再判断包含的情况*/
            return !TextUtils.isEmpty(rule.getIncludeRegular())
                    && Pattern.matches(rule.getIncludeRegular(), ymdw);
        } else {
            /*再判断包含的情况*/
            return !TextUtils.isEmpty(rule.getIncludeRegular())
                    && Pattern.matches(rule.getIncludeRegular(), ymdw);
        }
    }

    /**
     * 将两个时间组合起来
     */
    public static Date getStrokeTime(Date day, Date time) {
        Calendar dayCalender = Calendar.getInstance();
        dayCalender.setTime(day);

        Calendar timeCalender = Calendar.getInstance();
        timeCalender.setTime(time);

        timeCalender.set(Calendar.YEAR, dayCalender.get(Calendar.YEAR));
        timeCalender.set(Calendar.MONTH, dayCalender.get(Calendar.MONTH));
        timeCalender.set(Calendar.DAY_OF_MONTH, dayCalender.get(Calendar.DAY_OF_MONTH));
        return timeCalender.getTime();
    }


}
