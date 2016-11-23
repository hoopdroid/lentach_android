
package com.lentach.api.models.webapipost;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Attachment_ implements Parcelable {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("photo")
    @Expose
    public Photo_ photo;
    @SerializedName("page")
    @Expose
    public Page page;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeParcelable(this.photo, flags);
        dest.writeParcelable(this.page, flags);
    }

    public Attachment_() {
    }

    protected Attachment_(Parcel in) {
        this.type = in.readString();
        this.photo = in.readParcelable(Photo_.class.getClassLoader());
        this.page = in.readParcelable(Page.class.getClassLoader());
    }

    public static final Parcelable.Creator<Attachment_> CREATOR = new Parcelable.Creator<Attachment_>() {
        @Override
        public Attachment_ createFromParcel(Parcel source) {
            return new Attachment_(source);
        }

        @Override
        public Attachment_[] newArray(int size) {
            return new Attachment_[size];
        }
    };
}
