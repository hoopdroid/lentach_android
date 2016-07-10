package com.lentach.models;

public class UserFavoritePost {

    private String text;
    private String photoUrl;

    public UserFavoritePost() {
    }

    public UserFavoritePost(String text, String name, String photoUrl) {
        this.text = text;
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}