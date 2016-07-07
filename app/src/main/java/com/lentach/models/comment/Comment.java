
package com.lentach.models.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lentach.models.wallpost.Likes;


public class Comment {

    @SerializedName("cid")
    @Expose
    public Integer cid;
    @SerializedName("uid")
    @Expose
    public Integer uid;
    @SerializedName("from_id")
    @Expose
    public Integer fromId;
    @SerializedName("date")
    @Expose
    public Integer date;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("likes")
    @Expose
    public Likes likes;
    @SerializedName("reply_to_uid")
    @Expose
    public Integer replyToUid;
    @SerializedName("reply_to_cid")
    @Expose
    public Integer replyToCid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
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

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Integer getReplyToUid() {
        return replyToUid;
    }

    public void setReplyToUid(Integer replyToUid) {
        this.replyToUid = replyToUid;
    }

    public Integer getReplyToCid() {
        return replyToCid;
    }

    public void setReplyToCid(Integer replyToCid) {
        this.replyToCid = replyToCid;
    }
}
