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

        String dateString;

        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis(unix*1000);

        Calendar currentTimeCalendar = Calendar.getInstance();

        dateString = " " + mydate.get(Calendar.DAY_OF_MONTH) + "." +""+mydate.get(Calendar.MONTH);

        DateFormat.format("dd/MM/yyyy hh:mm:ssaa", unix * 1000L);

       //long postTime = TimeUnit.MILLISECONDS.toDays(unix*1000);
      //  long currentTime = TimeUnit.MILLISECONDS.toDays(currentTimeCalendar.getTimeInMillis());

      ///  if(postTime-currentTime<=)
         //   return dateString;
      //  int a =5;

        return  DateFormat.format("dd/MM/ HH:mm", unix * 1000L).toString();


    }


}
