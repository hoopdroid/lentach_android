
package com.lentach.api.models.webapipost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Comments implements Parcelable {

    @SerializedName("count")
    @Expose
    public Integer count;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.count);
    }

    public Comments() {
    }

    protected Comments(Parcel in) {
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Comments> CREATOR = new Parcelable.Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel source) {
            return new Comments(source);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };
}
