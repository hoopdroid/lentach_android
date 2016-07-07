package com.lentach.models.wallpost;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CopyHistory implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("from_id")
    @Expose
    private Integer fromId;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("post_type")
    @Expose
    private String postType;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = new ArrayList<Attachment>();
    @SerializedName("post_source")
    @Expose
    private PostSource postSource;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The ownerId
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     *
     * @param ownerId
     * The owner_id
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     *
     * @return
     * The fromId
     */
    public Integer getFromId() {
        return fromId;
    }

    /**
     *
     * @param fromId
     * The from_id
     */
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    /**
     *
     * @return
     * The date
     */
    public Integer getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The postType
     */
    public String getPostType() {
        return postType;
    }

    /**
     *
     * @param postType
     * The post_type
     */
    public void setPostType(String postType) {
        this.postType = postType;
    }

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The attachments
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     *
     * @param attachments
     * The attachments
     */
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    /**
     *
     * @return
     * The postSource
     */
    public PostSource getPostSource() {
        return postSource;
    }

    /**
     *
     * @param postSource
     * The post_source
     */
    public void setPostSource(PostSource postSource) {
        this.postSource = postSource;
    }


    protected CopyHistory(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        ownerId = in.readByte() == 0x00 ? null : in.readInt();
        fromId = in.readByte() == 0x00 ? null : in.readInt();
        date = in.readByte() == 0x00 ? null : in.readInt();
        postType = in.readString();
        text = in.readString();
        if (in.readByte() == 0x01) {
            attachments = new ArrayList<Attachment>();
            in.readList(attachments, Attachment.class.getClassLoader());
        } else {
            attachments = null;
        }
        postSource = (PostSource) in.readValue(PostSource.class.getClassLoader());
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
        if (ownerId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(ownerId);
        }
        if (fromId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(fromId);
        }
        if (date == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(date);
        }
        dest.writeString(postType);
        dest.writeString(text);
        if (attachments == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(attachments);
        }
        dest.writeValue(postSource);
    }

    @SuppressWarnings("unused")
    public static final Creator<CopyHistory> CREATOR = new Creator<CopyHistory>() {
        @Override
        public CopyHistory createFromParcel(Parcel in) {
            return new CopyHistory(in);
        }

        @Override
        public CopyHistory[] newArray(int size) {
            return new CopyHistory[size];
        }
    };
}