
package com.lentach.models.wallpost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostSource implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;

    /**
     *
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }


    protected PostSource(Parcel in) {
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Creator<PostSource> CREATOR = new Creator<PostSource>() {
        @Override
        public PostSource createFromParcel(Parcel in) {
            return new PostSource(in);
        }

        @Override
        public PostSource[] newArray(int size) {
            return new PostSource[size];
        }
    };
}
