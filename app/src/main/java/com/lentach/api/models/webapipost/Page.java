
package com.lentach.api.models.webapipost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Page implements Parcelable {

    @SerializedName("pid")
    @Expose
    public String pid;
    @SerializedName("gid")
    @Expose
    public Integer gid;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("view_url")
    @Expose
    public String viewUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pid);
        dest.writeValue(this.gid);
        dest.writeString(this.title);
        dest.writeString(this.viewUrl);
    }

    public Page() {
    }

    protected Page(Parcel in) {
        this.pid = in.readString();
        this.gid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.viewUrl = in.readString();
    }

    public static final Parcelable.Creator<Page> CREATOR = new Parcelable.Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel source) {
            return new Page(source);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };
}
