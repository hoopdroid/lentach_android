package com.lentach.util;


import com.lentach.ui.components.Constants;

public class FormatUtil {

    public static  String removeNewLinesFromPostText(String text,int count){

        text = text.replace("\n", " ").replace("\r", " ");

        if (text.length()<= Constants.POST_TEXT_LENGTH)
            return text;
        else
            return text.substring(0, Math.min(text.length(), count))+"...";
    }


}
