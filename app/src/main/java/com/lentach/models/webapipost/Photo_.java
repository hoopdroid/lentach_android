
package com.lentach.models.webapipost;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Photo_ {

    @SerializedName("pid")
    @Expose
    public Integer pid;
    @SerializedName("aid")
    @Expose
    public Integer aid;
    @SerializedName("owner_id")
    @Expose
    public Integer ownerId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("src")
    @Expose
    public String src;
    @SerializedName("src_big")
    @Expose
    public String srcBig;
    @SerializedName("src_small")
    @Expose
    public String srcSmall;
    @SerializedName("src_xbig")
    @Expose
    public String srcXbig;
    @SerializedName("src_xxbig")
    @Expose
    public String srcXxbig;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("created")
    @Expose
    public Integer created;
    @SerializedName("access_key")
    @Expose
    public String accessKey;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrcBig() {
        return srcBig;
    }

    public void setSrcBig(String srcBig) {
        this.srcBig = srcBig;
    }

    public String getSrcSmall() {
        return srcSmall;
    }

    public void setSrcSmall(String srcSmall) {
        this.srcSmall = srcSmall;
    }

    public String getSrcXbig() {
        return srcXbig;
    }

    public void setSrcXbig(String srcXbig) {
        this.srcXbig = srcXbig;
    }

    public String getSrcXxbig() {
        return srcXxbig;
    }

    public void setSrcXxbig(String srcXxbig) {
        this.srcXxbig = srcXxbig;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
