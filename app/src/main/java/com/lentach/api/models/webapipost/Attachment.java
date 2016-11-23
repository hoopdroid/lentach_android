
package com.lentach.api.models.webapipost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment implements Parcelable {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("photo")
    @Expose
    public Photo photo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeParcelable(this.photo, flags);
    }

    public Attachment() {
    }

    protected Attachment(Parcel in) {
        this.type = in.readString();
        this.photo = in.readParcelable(Photo.class.getClassLoader());
    }

    public static final Parcelable.Creator<Attachment> CREATOR = new Parcelable.Creator<Attachment>() {
        @Override
        public Attachment createFromParcel(Parcel source) {
            return new Attachment(source);
        }

        @Override
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };
}
