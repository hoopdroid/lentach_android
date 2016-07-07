package com.lentach.util;


import com.lentach.components.Constants;

public class FormatUtil {

    public static  String  formatPostText(String text){

        text = text.replace("\n", "").replace("\r", "");

        if (text.length()<= Constants.POST_TEXT_LENGTH)
            return text;
        else
            return text.substring(0, Math.min(text.length(), 100))+"...";
    }


}