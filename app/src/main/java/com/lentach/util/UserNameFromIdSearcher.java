package com.lentach.util;

import com.lentach.models.wallcomments.users.User;

/**
 * Created by ilyas on 6/29/2016.
 */

public class UserNameFromIdSearcher {

    public static String getUserName(int fromId, User user){
        String userName = String.valueOf(fromId);


        if(user.getId()==fromId)
            userName = user.getFirstName()+" "+user.getLastName();


        return userName;
    }
}
