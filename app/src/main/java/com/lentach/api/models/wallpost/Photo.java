
package com.lentach.api.models.wallpost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Photo implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("album_id")
    @Expose
    private Integer albumId;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("photo_75")
    @Expose
    private String photo75;
    @SerializedName("photo_130")
    @Expose
    private String photo130;
    @SerializedName("photo_604")
    @Expose
    private String photo604;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("access_key")
    @Expose
    private String accessKey;

    public Photo(String photo604) {
        this.photo604 = photo604;
    }

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
     *     The albumId
     */
    public Integer getAlbumId() {
        return albumId;
    }

    /**
     *
     * @param albumId
     *     The album_id
     */
    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
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
     *     The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     *     The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     *     The photo75
     */
    public String getPhoto75() {
        return photo75;
    }

    /**
     *
     * @param photo75
     *     The photo_75
     */
    public void setPhoto75(String photo75) {
        this.photo75 = photo75;
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
     *     The photo604
     */
    public String getPhoto604() {
        return photo604;
    }

    /**
     *
     * @param photo604
     *     The photo_604
     */
    public void setPhoto604(String photo604) {
        this.photo604 = photo604;
    }

    /**
     *
     * @return
     *     The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     *
     * @param width
     *     The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     *
     * @return
     *     The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     *
     * @param height
     *     The height
     */
    public void setHeight(Integer height) {
        this.height = height;
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


    protected Photo(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        albumId = in.readByte() == 0x00 ? null : in.readInt();
        ownerId = in.readByte() == 0x00 ? null : in.readInt();
        userId = in.readByte() == 0x00 ? null : in.readInt();
        photo75 = in.readString();
        photo130 = in.readString();
        photo604 = in.readString();
        width = in.readByte() == 0x00 ? null : in.readInt();
        height = in.readByte() == 0x00 ? null : in.readInt();
        text = in.readString();
        date = in.readByte() == 0x00 ? null : in.readInt();
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
        if (albumId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(albumId);
        }
        if (ownerId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(ownerId);
        }
        if (userId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(userId);
        }
        dest.writeString(photo75);
        dest.writeString(photo130);
        dest.writeString(photo604);
        if (width == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(width);
        }
        if (height == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(height);
        }
        dest.writeString(text);
        if (date == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(date);
        }
        dest.writeString(accessKey);
    }

    @SuppressWarnings("unused")
    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
