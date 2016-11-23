
package com.lentach.repository.realmmodel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class Likes extends RealmObject {


    @SerializedName("count")
    @Expose
    private Integer count;

    /**
     *
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }



}
