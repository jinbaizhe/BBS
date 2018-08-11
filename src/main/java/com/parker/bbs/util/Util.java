package com.parker.bbs.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {
    public static String getCurrentDateTime()
    {
        SimpleDateFormat f =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar=Calendar.getInstance();
        return f.format(calendar.getTime());
    }

    public static String getActiveCode(int num)
    {
        String key= "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder value = new StringBuilder();
        for(int i=0;i<num;i++)
            value.append(key.charAt( (int)( Math.random()*key.length() ) ));
        return value.toString();
    }

}
