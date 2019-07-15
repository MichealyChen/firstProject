package com.chenyongxiu.firstproject.common;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DataUtil {

    public static  boolean isNumber(String string) {
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        return string.chars().allMatch(Character::isDigit);
    }

    public void dateConvert(){
        // 输出当前时间：2019-04-24T19:41:21.858
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

// 格式化时间，输出：2019年04月24日 19:49:25
        LocalDateTime time = LocalDateTime.now();
        String timeStr = time.format(DateTimeFormatter.ofPattern("yyyy年年MM月dd日 HH:mm:ss"));
        System.out.println(timeStr);

// 当前时间增加20分钟
        LocalDateTime afterPlusTime = LocalDateTime.now().plus(20, ChronoUnit.MINUTES);
        System.out.println(afterPlusTime);

// 创建开始和结束时间，并计算中间差值
        LocalDateTime start = LocalDateTime.of(1992, 8, 13, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2019, 4, 25, 0, 0);
        long diff = getDiff(start, end);
        System.out.println(diff+"年"); // 26

        boolean b = start.isAfter(end);
        System.out.println(b); // false

//获取秒数 （东8区，也就是北京时间）
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
//获取毫秒数（东8区，也就是北京时间）
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        Instant now1 = Instant.now(); // 2019-04-24T12:30:34.349Z
        long currentSecond = now1.getEpochSecond(); // 秒数：1556109034
        long currentMilli = now1.toEpochMilli(); // 毫秒数：1556109034349

        Instant afterPlusSecondInstant = now1.plusSeconds(1000); // 秒数增加1000
        boolean b2 = now.isBefore(ChronoLocalDateTime.from(afterPlusSecondInstant));
    }

    private long getDiff(LocalDateTime start, LocalDateTime end) {
        int daysNum = Period.between(start.toLocalDate(), end.toLocalDate()).getDays();
        int monthNum = Period.between(start.toLocalDate(), end.toLocalDate()).getMonths();
        return monthNum;
    }

    public void test2(){
        Instant instant  = Instant.now();
        Date date1 = Date.from(instant); // Instant转换为Date
        Instant instant2 = date1.toInstant(); // Date转换为Instant

// Date 与 LocalDateTime 的转换是通过 Instant 中间的转换来进行的
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault()); // Date转换为LocalDateTime

        LocalDateTime localDateTime1 = LocalDateTime.now();
        Instant instant3 = localDateTime1.atZone(ZoneId.systemDefault()).toInstant();
        Date date4 = Date.from(instant); // LocalDateTime转换为Date
    }
}
