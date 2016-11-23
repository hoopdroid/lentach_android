
package com.lentach.api.models.webapipost;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes implements Parcelable {

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

    public Likes() {
    }

    protected Likes(Parcel in) {
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Likes> CREATOR = new Parcelable.Creator<Likes>() {
        @Override
        public Likes createFromParcel(Parcel source) {
            return new Likes(source);
        }

        @Override
        public Likes[] newArray(int size) {
            return new Likes[size];
        }
    };
}
