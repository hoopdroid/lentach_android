package com.lentach.util;


import com.lentach.api.models.wallcomments.WallComment;

import java.util.Comparator;

/**
 * A custom comporator for {@link WallComment} objects.
 */

public class CommentsComporator implements Comparator<WallComment> {

    public int compare(WallComment wallCommentItem1, WallComment wallCommentItem2) {
        Integer obj1 = wallCommentItem1.getLikes().getCount();
        Integer obj2 = wallCommentItem2.getLikes().getCount();

        if (obj1 == obj2) {
            return 0;
        }
        if (obj1 == null) {
            return -1;
        }
        if (obj2 == null) {
            return 1;
        }
        return obj2.compareTo(obj1);
    }
}