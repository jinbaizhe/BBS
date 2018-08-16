package com.parker.bbs.util;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {
    public static String getActiveCode(int num)
    {
        String key= "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder value = new StringBuilder();
        for(int i=0;i<num;i++)
            value.append(key.charAt( (int)( Math.random()*key.length() ) ));
        return value.toString();
    }

    public static void addReferURL(String referURL, HttpSession session){
        if (referURL!=null){
            session.setAttribute("referURL", referURL);
        }
    }
}
