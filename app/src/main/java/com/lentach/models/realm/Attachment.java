
package com.lentach.models.realm;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class Attachment extends RealmObject {


    private String type;

    private String photo;

    private String page;

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
    public String getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     *     The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     *     The page
     */
    public String getPage() {
        return page;
    }

    /**
     *
     * @param page
     *     The page
     */
    public void setPage(String page) {
        this.page = page;
    }



}