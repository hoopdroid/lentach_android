package com.lentach.components;


import com.lentach.models.wallcomments.WallComment;
import com.lentach.models.wallpost.Post;

import java.util.Comparator;

/**
 * A custom comporator for {@link WallComment} objects.
 */

public class PostsLikeComporator implements Comparator<Post> {

    public int compare(Post wallItem1, Post wallItem2) {
        Integer obj1 = wallItem1.getLikes().getCount();
        Integer obj2 = wallItem2.getLikes().getCount();

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