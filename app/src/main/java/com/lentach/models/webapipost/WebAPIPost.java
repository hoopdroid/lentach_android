
package com.lentach.models.webapipost;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WebAPIPost {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("from_id")
    @Expose
    public Integer fromId;
    @SerializedName("to_id")
    @Expose
    public Integer toId;
    @SerializedName("date")
    @Expose
    public Integer date;
    @SerializedName("post_type")
    @Expose
    public String postType;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("attachment")
    @Expose
    public Attachment attachment;
    @SerializedName("attachments")
    @Expose
    public List<Attachment_> attachments = new ArrayList<Attachment_>();
    @SerializedName("comments")
    @Expose
    public Comments comments;
    @SerializedName("likes")
    @Expose
    public Likes likes;
    @SerializedName("reposts")
    @Expose
    public Reposts reposts;
    @SerializedName("copy_post_date")
    @Expose
    public Integer copyPostDate;
    @SerializedName("copy_post_type")
    @Expose
    public String copyPostType;
    @SerializedName("copy_owner_id")
    @Expose
    public Integer copyOwnerId;
    @SerializedName("copy_post_id")
    @Expose
    public Integer copyPostId;
    @SerializedName("copy_text")
    @Expose
    public String copyText;

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

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public List<Attachment_> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment_> attachments) {
        this.attachments = attachments;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public void setReposts(Reposts reposts) {
        this.reposts = reposts;
    }
}
