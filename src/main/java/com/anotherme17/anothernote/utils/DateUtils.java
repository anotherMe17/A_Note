package com.anotherme17.anothernote.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils
 */
public class DateUtils {

    public static String getYMDW(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) calendar.setTime(date);
        return String.format("%s-%s-%s-%s",
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.DAY_OF_WEEK));
    }
}
