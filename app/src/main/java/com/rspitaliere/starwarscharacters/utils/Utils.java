package com.rspitaliere.starwarscharacters.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rspitaliere on 23/01/18.
 */

public class Utils {

    public static long getTimeLong(){
        return Calendar.getInstance(TimeZone.getDefault()).getTime().getTime();
    }

    public static String formatDate(Long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd/MM - HH'h'mm");
        return format.format(date);
    }
}
