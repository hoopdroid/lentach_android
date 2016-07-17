package com.lentach.util;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ilyas on 6/25/2016.
 */

public class UnixConverter {


    public static String convertToString(int unix){

        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis(unix*1000);
        DateFormat.format("dd/MM/yyyy hh:mm:ssaa", unix * 1000L);

        return  DateFormat.format("dd/MM/ HH:mm", unix * 1000L).toString();


    }


}
