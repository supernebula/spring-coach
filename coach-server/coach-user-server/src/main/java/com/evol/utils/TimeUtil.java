package com.evol.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class TimeUtil {

    public TimeUtil() {
    }

    public static final String YY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YY_MM_DD = "yyyy-MM-dd";


    public static Date getExpiration(long outTime) {
        long strExpiration = System.currentTimeMillis() + outTime;
        return new Date(strExpiration);
    }

    public static Date now() {
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(YY_MM_DD_HH_MM_SS);
        String strNowTime = yyyyMMddHHmmss.format(Calendar.getInstance().getTime());
        try {
            return yyyyMMddHHmmss.parse(strNowTime);
        } catch (ParseException e) {
            log.error(" DateHelper getParseDate ", e);
        }
        return null;
    }

}
