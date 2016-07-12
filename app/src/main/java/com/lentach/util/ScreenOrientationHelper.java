package com.lentach.util;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.Display;

/**
 * Created by Илья on 11.07.2016.
 */

public class ScreenOrientationHelper {

    public static int getScreenOrientation(Activity activity)
    {
        Display getOrient = activity.getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if(getOrient.getWidth()==getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_SQUARE;
        } else{
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return orientation;
    }
}
