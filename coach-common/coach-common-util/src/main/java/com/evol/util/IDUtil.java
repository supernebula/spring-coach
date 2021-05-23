package com.evol.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 带日期的分布式唯一ID生成工具类
 * no = 日期时间 + 雪花算法
 */
public class IDUtil {



    static {
        //跳过第一次懒加载
        IdWorker.getIdStr();
    }

    /**
     *
     * @param timeFormat yyyyMMddHHmmssSSS, 根据需要删减
     * @return
     */
    public static String IdNo(String timeFormat){
        DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern(timeFormat);
        return LocalDateTime.now().format(hourFormat) + IdWorker.getIdStr();
    }

    /**
     *
     * @return 返回29位唯一编号，带日期精确到小时
     */
    public static String hourIdNo(){
        DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("yyyyMMddHH");
        return LocalDateTime.now().format(hourFormat) + IdWorker.getIdStr();
    }

    /**
     *
     * @return 返回29位唯一编号，带日期精确到小时
     */
    public static String minuteIdNo(){
        DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return LocalDateTime.now().format(hourFormat) + IdWorker.getIdStr();
    }

    public static void main(String[] args) {
        String no = IdNo("yyyyMMddHHmmssSSS");
        System.out.println(no + "，长度：" + no.length());
       //202105201635156351395297053714030593，长度：36

        String hourIdNo = hourIdNo();
        System.out.println("TS" + hourIdNo + "，长度：" + hourIdNo.length());
        //TS20210520161395297053714030594，长度：29

        String minuteIdNo = minuteIdNo();
        System.out.println(minuteIdNo + "，长度：" + minuteIdNo.length());
        //2021052016351395297053714030595，长度：31
    }

}
