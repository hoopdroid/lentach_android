package com.lentach.repository.realmmodel;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ilyas on 7/1/2016.
 */

public class PostRealmModel extends RealmObject {

    @PrimaryKey
    private Integer id;

    private Integer fromId;

    private Integer ownerId;

    private Integer date;

    private String postType;

    private String text;

    private Integer isPinned;

    private String photoAttach;

    private int likes;


    public PostRealmModel() {
    }

    public PostRealmModel(Integer id, Integer fromId, Integer ownerId, Integer date,
                          String postType, String text, String photoAttach, Integer isPinned,
                          int likes) {
        this.id = id;
        this.fromId = fromId;
        this.ownerId = ownerId;
        this.date = date;
        this.postType = postType;
        this.text = text;
        this.photoAttach = photoAttach;
        this.isPinned = isPinned;
        this.likes = likes;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(Integer isPinned) {
        this.isPinned = isPinned;
    }

    public String getPhotoAttach() {
        return photoAttach;
    }

    public void setPhotoAttach(String photoAttach) {
        this.photoAttach = photoAttach;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}