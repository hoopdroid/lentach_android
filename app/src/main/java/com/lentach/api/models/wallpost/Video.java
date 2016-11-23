package com.lentach.api.models.wallpost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("photo_130")
    @Expose
    private String photo130;
    @SerializedName("photo_320")
    @Expose
    private String photo320;
    @SerializedName("photo_800")
    @Expose
    private String photo800;
    @SerializedName("access_key")
    @Expose
    private String accessKey;

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
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     *     The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
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
     *     The views
     */
    public Integer getViews() {
        return views;
    }

    /**
     *
     * @param views
     *     The views
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     *
     * @return
     *     The comments
     */
    public Integer getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     *     The comments
     */
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     *     The photo130
     */
    public String getPhoto130() {
        return photo130;
    }

    /**
     *
     * @param photo130
     *     The photo_130
     */
    public void setPhoto130(String photo130) {
        this.photo130 = photo130;
    }

    /**
     *
     * @return
     *     The photo320
     */
    public String getPhoto320() {
        return photo320;
    }

    /**
     *
     * @param photo320
     *     The photo_320
     */
    public void setPhoto320(String photo320) {
        this.photo320 = photo320;
    }

    /**
     *
     * @return
     *     The photo800
     */
    public String getPhoto800() {
        return photo800;
    }

    /**
     *
     * @param photo800
     *     The photo_800
     */
    public void setPhoto800(String photo800) {
        this.photo800 = photo800;
    }

    /**
     *
     * @return
     *     The accessKey
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     *
     * @param accessKey
     *     The access_key
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }


    protected Video(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        ownerId = in.readByte() == 0x00 ? null : in.readInt();
        title = in.readString();
        duration = in.readByte() == 0x00 ? null : in.readInt();
        description = in.readString();
        date = in.readByte() == 0x00 ? null : in.readInt();
        views = in.readByte() == 0x00 ? null : in.readInt();
        comments = in.readByte() == 0x00 ? null : in.readInt();
        photo130 = in.readString();
        photo320 = in.readString();
        photo800 = in.readString();
        accessKey = in.readString();
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
        dest.writeString(title);
        if (duration == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(duration);
        }
        dest.writeString(description);
        if (date == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(date);
        }
        if (views == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(views);
        }
        if (comments == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(comments);
        }
        dest.writeString(photo130);
        dest.writeString(photo320);
        dest.writeString(photo800);
        dest.writeString(accessKey);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}