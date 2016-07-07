package com.lentach.components;


import com.lentach.models.wallcomments.WallComment;

import java.util.Comparator;

/**
 * A custom comporator for {@link WallComment} objects.
 */

public class IDCommentsComporator implements Comparator<WallComment> {

    public int compare(WallComment wallCommentItem1, WallComment wallCommentItem2) {
        Integer obj1 = wallCommentItem1.getFromId();
        Integer obj2 = wallCommentItem2.getFromId();

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