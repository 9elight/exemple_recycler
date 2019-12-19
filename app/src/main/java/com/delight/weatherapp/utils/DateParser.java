package com.delight.weatherapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {
    public static String parseDateToTime(double d){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date();
        date.setTime((long) d * 1000);
        return dateFormat.format(date.getTime());
    }
    public static String forCastDate(String s) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(s);

        SimpleDateFormat outDt = new SimpleDateFormat("dd.MMM");
        String parseDate = outDt.format(date);
        return parseDate;
    }
    }
