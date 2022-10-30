package com.lb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date convertDateFromString(String dateStr) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
    }
}
