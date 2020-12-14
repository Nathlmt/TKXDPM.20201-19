package org.tkxdpm20201.Nhom19.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static String format(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = formatter.format(date);
        System.out.println(strDate);
        return strDate;
    }

    public static String format(LocalDateTime localDateTime){
        return format(convertToDateViaSqlTimestamp(localDateTime));
    }

    public static long subtractTime(LocalDateTime startDate, LocalDateTime endDate){
        long start = startDate.atZone(ZoneOffset.UTC).toEpochSecond();
        long end = endDate.atZone(ZoneOffset.UTC).toEpochSecond();
        return end - start;
    }

    public static LocalDateTime reverse(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

//    public static Date reverse(String date){
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        return LocalDateTime.parse(date, formatter);
//    }

    private static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }



}
