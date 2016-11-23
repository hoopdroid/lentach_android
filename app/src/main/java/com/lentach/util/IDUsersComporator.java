package com.lentach.util;


import com.lentach.api.models.wallcomments.WallComment;
import com.lentach.api.models.wallcomments.users.User;

import java.util.Comparator;

/**
 * A custom comporator for {@link WallComment} objects.
 */

public class IDUsersComporator implements Comparator<User> {

    public int compare(User userItem1, User userItem2) {
        Integer obj1 = userItem1.getId();
        Integer obj2 = userItem2.getId();

        if (obj1 == obj2) {
            return 0;
        }
        if (obj1 == null) {
            return -1;
        }
        if (obj2 == null) {
            return 1;
        }
        return obj1.compareTo(obj2);
    }
}