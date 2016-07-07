package com.lentach.components;


import com.lentach.models.comment.Comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that holds collection of Artist models.
 */

public class TopCommentsController {

    private List<Comment> mArtistsList = new ArrayList<>();

    public ArrayList<Comment> getArtistsList() {
        return (ArrayList) mArtistsList;
    }

    public void setArtistsList(List<Comment> mArtistsList) {
        this.mArtistsList = mArtistsList;
    }




}
