
package com.lentach.models.webapipost;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Page {

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

}
