
package com.lentach.models.wallpost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Attachment implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private Photo photo;
    @SerializedName("page")
    @Expose
    private Page page;

    public Attachment(String type, Photo photo) {
        this.type = type;
        this.photo = photo;
    }

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

    /**
     *
     * @return
     *     The photo
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     *     The photo
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     *     The page
     */
    public Page getPage() {
        return page;
    }

    /**
     *
     * @param page
     *     The page
     */
    public void setPage(Page page) {
        this.page = page;
    }


    protected Attachment(Parcel in) {
        type = in.readString();
        photo = (Photo) in.readValue(Photo.class.getClassLoader());
        page = (Page) in.readValue(Page.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeValue(photo);
        dest.writeValue(page);
    }

    @SuppressWarnings("unused")
    public static final Creator<Attachment> CREATOR = new Creator<Attachment>() {
        @Override
        public Attachment createFromParcel(Parcel in) {
            return new Attachment(in);
        }

        @Override
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };
}