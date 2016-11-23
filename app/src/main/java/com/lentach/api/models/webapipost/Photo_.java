
package com.lentach.api.models.webapipost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Photo_ implements Parcelable {

    @SerializedName("pid")
    @Expose
    public Integer pid;
    @SerializedName("aid")
    @Expose
    public Integer aid;
    @SerializedName("owner_id")
    @Expose
    public Integer ownerId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("src")
    @Expose
    public String src;
    @SerializedName("src_big")
    @Expose
    public String srcBig;
    @SerializedName("src_small")
    @Expose
    public String srcSmall;
    @SerializedName("src_xbig")
    @Expose
    public String srcXbig;
    @SerializedName("src_xxbig")
    @Expose
    public String srcXxbig;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("created")
    @Expose
    public Integer created;
    @SerializedName("access_key")
    @Expose
    public String accessKey;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrcBig() {
        return srcBig;
    }

    public void setSrcBig(String srcBig) {
        this.srcBig = srcBig;
    }

    public String getSrcSmall() {
        return srcSmall;
    }

    public void setSrcSmall(String srcSmall) {
        this.srcSmall = srcSmall;
    }

    public String getSrcXbig() {
        return srcXbig;
    }

    public void setSrcXbig(String srcXbig) {
        this.srcXbig = srcXbig;
    }

    public String getSrcXxbig() {
        return srcXxbig;
    }

    public void setSrcXxbig(String srcXxbig) {
        this.srcXxbig = srcXxbig;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.srcXxbig);
        dest.writeValue(this.width);
        dest.writeValue(this.height);
        dest.writeString(this.text);
        dest.writeValue(this.pid);
        dest.writeValue(this.aid);
        dest.writeValue(this.ownerId);
        dest.writeValue(this.userId);
        dest.writeString(this.src);
        dest.writeString(this.srcBig);
        dest.writeString(this.srcSmall);
        dest.writeString(this.srcXbig);
        dest.writeValue(this.created);
        dest.writeString(this.accessKey);
    }

    public Photo_() {
    }

    protected Photo_(Parcel in) {
        this.srcXxbig = in.readString();
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.text = in.readString();
        this.pid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.aid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ownerId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.src = in.readString();
        this.srcBig = in.readString();
        this.srcSmall = in.readString();
        this.srcXbig = in.readString();
        this.created = (Integer) in.readValue(Integer.class.getClassLoader());
        this.accessKey = in.readString();
    }

    public static final Parcelable.Creator<Photo_> CREATOR = new Parcelable.Creator<Photo_>() {
        @Override
        public Photo_ createFromParcel(Parcel source) {
            return new Photo_(source);
        }

        @Override
        public Photo_[] newArray(int size) {
            return new Photo_[size];
        }
    };
}
