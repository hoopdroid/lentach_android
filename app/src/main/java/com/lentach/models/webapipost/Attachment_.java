
package com.lentach.models.webapipost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Attachment_ {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("photo")
    @Expose
    public Photo_ photo;
    @SerializedName("page")
    @Expose
    public Page page;

}
