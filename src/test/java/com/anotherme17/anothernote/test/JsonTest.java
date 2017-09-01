package com.anotherme17.anothernote.test;

import com.alibaba.fastjson.JSON;
import com.anotherme17.anothernote.entity.PlanRule;
import com.anotherme17.anothernote.utils.DateUtils;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 */
public class JsonTest {

    @Test
    public void testPlanRuleJson() {
        PlanRule rule = new PlanRule();
        Calendar calendar = Calendar.getInstance();
        rule.setStartTime(calendar.getTime());
        calendar.add(Calendar.HOUR, 2);
        rule.setEndTime(calendar.getTime());
        rule.setIncludeRegular("[0-9]*-[0-9]*-[0-9]*-[0-7]*");
        System.out.println(JSON.toJSONString(rule));
    }

    @Test
    public void testRegular() {
        String ymdw = DateUtils.getYMDW(new Date());
        System.out.println(ymdw);
        System.out.println(Pattern.matches("[0-9]*-[0-9]*-[0-9]*-[0-7]*", ymdw));
    }

    @Test
    public void testDate() {
        Date date = new Date();
        System.out.println(DateUtils.formatDate(date));

        String t = DateUtils.formatDay(date);
        try {
            date = DateUtils.parseDay(t);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(DateUtils.formatDate(date));
    }
}
