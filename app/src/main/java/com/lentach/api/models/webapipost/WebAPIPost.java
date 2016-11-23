
package com.lentach.api.models.webapipost;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WebAPIPost implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.fromId);
        dest.writeValue(this.toId);
        dest.writeValue(this.date);
        dest.writeString(this.postType);
        dest.writeString(this.text);
        dest.writeParcelable(this.attachment, flags);
        dest.writeList(this.attachments);
        dest.writeParcelable(this.comments, flags);
        dest.writeParcelable(this.likes, flags);
        dest.writeParcelable(this.reposts, flags);
        dest.writeValue(this.copyPostDate);
        dest.writeString(this.copyPostType);
        dest.writeValue(this.copyOwnerId);
        dest.writeValue(this.copyPostId);
        dest.writeString(this.copyText);
    }

    public WebAPIPost() {
    }

    protected WebAPIPost(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fromId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.toId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.date = (Integer) in.readValue(Integer.class.getClassLoader());
        this.postType = in.readString();
        this.text = in.readString();
        this.attachment = in.readParcelable(Attachment.class.getClassLoader());
        this.attachments = new ArrayList<Attachment_>();
        in.readList(this.attachments, Attachment_.class.getClassLoader());
        this.comments = in.readParcelable(Comments.class.getClassLoader());
        this.likes = in.readParcelable(Likes.class.getClassLoader());
        this.reposts = in.readParcelable(Reposts.class.getClassLoader());
        this.copyPostDate = (Integer) in.readValue(Integer.class.getClassLoader());
        this.copyPostType = in.readString();
        this.copyOwnerId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.copyPostId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.copyText = in.readString();
    }

    public static final Parcelable.Creator<WebAPIPost> CREATOR = new Parcelable.Creator<WebAPIPost>() {
        @Override
        public WebAPIPost createFromParcel(Parcel source) {
            return new WebAPIPost(source);
        }

        @Override
        public WebAPIPost[] newArray(int size) {
            return new WebAPIPost[size];
        }
    };
}
