
package com.lentach.models.wallpost;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Post implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("from_id")
    @Expose
    private Integer fromId;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("post_type")
    @Expose
    private String postType;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("is_pinned")
    @Expose
    private Integer isPinned;
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = new ArrayList<Attachment>();
    @SerializedName("comments")
    @Expose
    private Comments comments;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("reposts")
    @Expose
    private Reposts reposts;
    @SerializedName("copy_history")
    @Expose
    private List<CopyHistory> copyHistory = new ArrayList<CopyHistory>();

    /**
     *
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
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
     *     The ownerId
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     *
     * @param ownerId
     *     The owner_id
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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
     *     The postType
     */
    public String getPostType() {
        return postType;
    }

    /**
     *
     * @param postType
     *     The post_type
     */
    public void setPostType(String postType) {
        this.postType = postType;
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
     *     The isPinned
     */
    public Integer getIsPinned() {
        return isPinned;
    }

    /**
     *
     * @param isPinned
     *     The is_pinned
     */
    public void setIsPinned(Integer isPinned) {
        this.isPinned = isPinned;
    }

    /**
     *
     * @return
     *     The attachments
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     *
     * @param attachments
     *     The attachments
     */
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    /**
     *
     * @return
     *     The comments
     */
    public Comments getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     *     The comments
     */
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     *     The likes
     */
    public Likes getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     *     The likes
     */
    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    /**
     *
     * @return
     *     The reposts
     */
    public Reposts getReposts() {
        return reposts;
    }

    /**
     *
     * @param reposts
     *     The reposts
     */
    public void setReposts(Reposts reposts) {
        this.reposts = reposts;
    }

    /**
     *
     * @return
     *     The copyHistory
     */
    public List<CopyHistory> getCopyHistory() {
        return copyHistory;
    }

    /**
     *
     * @param copyHistory
     *     The copy_history
     */
    public void setCopyHistory(List<CopyHistory> copyHistory) {
        this.copyHistory = copyHistory;
    }


    protected Post(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        fromId = in.readByte() == 0x00 ? null : in.readInt();
        ownerId = in.readByte() == 0x00 ? null : in.readInt();
        date = in.readByte() == 0x00 ? null : in.readInt();
        postType = in.readString();
        text = in.readString();
        isPinned = in.readByte() == 0x00 ? null : in.readInt();
        if (in.readByte() == 0x01) {
            attachments = new ArrayList<Attachment>();
            in.readList(attachments, Attachment.class.getClassLoader());
        } else {
            attachments = null;
        }
        comments = (Comments) in.readValue(Comments.class.getClassLoader());
        likes = (Likes) in.readValue(Likes.class.getClassLoader());
        reposts = (Reposts) in.readValue(Reposts.class.getClassLoader());
        if (in.readByte() == 0x01) {
            copyHistory = new ArrayList<CopyHistory>();
            in.readList(copyHistory, CopyHistory.class.getClassLoader());
        } else {
            copyHistory = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        if (fromId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(fromId);
        }
        if (ownerId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(ownerId);
        }
        if (date == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(date);
        }
        dest.writeString(postType);
        dest.writeString(text);
        if (isPinned == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(isPinned);
        }
        if (attachments == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(attachments);
        }
        dest.writeValue(comments);
        dest.writeValue(likes);
        dest.writeValue(reposts);
        if (copyHistory == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(copyHistory);
        }
    }

    public Post(Integer id, Integer fromId, Integer ownerId,
                String postType, Integer date, String text, Integer isPinned,
                List<Attachment> attachments, Likes likes) {
        this.id = id;
        this.fromId = fromId;
        this.ownerId = ownerId;
        this.postType = postType;
        this.date = date;
        this.text = text;
        this.isPinned = isPinned;
        this.attachments = attachments;
        this.likes = likes;
    }

    @SuppressWarnings("unused")
    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };


    public int getPhotoAmount(){
        int amount = 0 ;
        if(attachments!=null)
        for (int i = 0; i < getAttachments().size() ; i++) {

            if(getAttachments().get(i).getType()!=null)
            if(getAttachments().get(i).getType().equals("photo")){

                amount++;}

        }
        return  amount;
    }
}
