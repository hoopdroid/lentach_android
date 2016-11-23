
package com.lentach.api.models.webapipost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Photo implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.pid);
        dest.writeValue(this.aid);
        dest.writeValue(this.ownerId);
        dest.writeValue(this.userId);
        dest.writeString(this.src);
        dest.writeString(this.srcBig);
        dest.writeString(this.srcSmall);
        dest.writeString(this.srcXbig);
        dest.writeString(this.srcXxbig);
        dest.writeValue(this.width);
        dest.writeValue(this.height);
        dest.writeString(this.text);
        dest.writeValue(this.created);
        dest.writeString(this.accessKey);
    }

    public Photo() {
    }

    protected Photo(Parcel in) {
        this.pid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.aid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ownerId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.src = in.readString();
        this.srcBig = in.readString();
        this.srcSmall = in.readString();
        this.srcXbig = in.readString();
        this.srcXxbig = in.readString();
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.text = in.readString();
        this.created = (Integer) in.readValue(Integer.class.getClassLoader());
        this.accessKey = in.readString();
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
