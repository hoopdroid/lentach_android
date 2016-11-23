
package com.lentach.api.models.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lentach.api.models.webapipost.WebAPIPost;


public class Comment {

    @SerializedName("cid")
    @Expose
    private Integer cid;
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("from_id")
    @Expose
    private Integer fromId;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("likes")
    @Expose
    private com.lentach.api.models.webapipost.Likes likes;
    @SerializedName("post")
    @Expose
    private WebAPIPost post;

    /**
     *
     * @return
     *     The cid
     */
    public Integer getCid() {
        return cid;
    }

    /**
     *
     * @param cid
     *     The cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     *
     * @return
     *     The uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     *
     * @param uid
     *     The uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     *
     * @return
     *     The fromId
     */
    public Integer getFromId() {
        return fromId;
    }

    /**
     *
     * @param fromId
     *     The from_id
     */
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    /**
     *
     * @return
     *     The date
     */
    public Integer getDate() {
        return date;
    }

    /**
     *
     * @param date
     *     The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     *
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     *     The likes
     */
    public com.lentach.api.models.webapipost.Likes getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     *     The likes
     */
    public void setLikes(com.lentach.api.models.webapipost.Likes likes) {
        this.likes = likes;
    }

    /**
     *
     * @return
     *     The post
     */
    public WebAPIPost getPost() {
        return post;
    }

    /**
     *
     * @param post
     *     The post
     */
    public void setPost(WebAPIPost post) {
        this.post = post;
    }
}
