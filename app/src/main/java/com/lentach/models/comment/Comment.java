
package com.lentach.models.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Comment {

    @SerializedName("cid")
    @Expose
    public Integer cid;
    @SerializedName("uid")
    @Expose
    public Integer uid;
    @SerializedName("from_id")
    @Expose
    public Integer fromId;
    @SerializedName("date")
    @Expose
    public Integer date;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("likes")
    @Expose
    public Likes likes;
    @SerializedName("reply_to_uid")
    @Expose
    public Integer replyToUid;
    @SerializedName("reply_to_cid")
    @Expose
    public Integer replyToCid;

}
