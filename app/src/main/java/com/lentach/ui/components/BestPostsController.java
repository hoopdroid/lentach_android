package com.lentach.ui.components;


import com.lentach.api.models.wallpost.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that holds collection of Post models.
 */

public class BestPostsController {

    private int offset = 0;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<Post> getmNewPostsList() {
        return mNewPostsList;
    }

    public void setmNewPostsList(List<Post> mNewPostsList) {
        this.mNewPostsList = mNewPostsList;
    }

    private List<Post> mNewPostsList = new ArrayList<>();

    public ArrayList<Post> getNewPostsList() {
        return (ArrayList) mNewPostsList;
    }

    public void setNewPostsList(List<Post> mArtistsList) {
        this.mNewPostsList = mArtistsList;
    }

    public void addNewPosts(List<Post> mNewPostsList) {
        this.mNewPostsList.addAll(mNewPostsList);
    }


}
