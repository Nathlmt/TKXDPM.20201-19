package org.tkxdpm20201.Nhom19.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateUtil {

    /**
     * format date following yyyy-MM-dd hh:mm:ss pattern
     * @param date
     * @return string date has formatted
     */
    public static String format(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = formatter.format(date);
        System.out.println(strDate);
        return strDate;
    }

    public static String format(LocalDateTime localDateTime){
        return format(convertToDateViaSqlTimestamp(localDateTime));
    }

    public static Timestamp toTimestamp(LocalDateTime time){
        return java.sql.Timestamp.valueOf(time);
    }

    private static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }



}
