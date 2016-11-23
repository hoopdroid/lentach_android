
package com.lentach.api.models.webapipost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Reposts implements Parcelable {

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

    public Reposts() {
    }

    protected Reposts(Parcel in) {
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Reposts> CREATOR = new Parcelable.Creator<Reposts>() {
        @Override
        public Reposts createFromParcel(Parcel source) {
            return new Reposts(source);
        }

        @Override
        public Reposts[] newArray(int size) {
            return new Reposts[size];
        }
    };
}
