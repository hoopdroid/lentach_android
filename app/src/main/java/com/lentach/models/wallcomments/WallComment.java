
package com.lentach.models.wallcomments;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lentach.models.wallcomments.users.User;
import com.lentach.models.wallpost.Attachment;
import com.lentach.models.wallpost.Likes;
import com.lentach.util.UserNameFromIdSearcher;


public class WallComment implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("from_id")
    @Expose
    private Integer fromId;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = new ArrayList<Attachment>();
    @SerializedName("reply_to_user")
    @Expose
    private Integer replyToUser;
    @SerializedName("reply_to_comment")
    @Expose
    private Integer replyToComment;
    @SerializedName("likes")
    @Expose
    private Likes likes;

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(User user) {
        this.username = user.getFirstName()+" "+user.getLastName();
    }

    /**
     *
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The fromId
     */
    public Integer getFromId() {
        return fromId;
    }

    /**
     *
     * @param fromId
     *     The from_id
     */
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    /**
     *
     * @return
     *     The date
     */
    public Integer getDate() {
        return date;
    }

    /**
     *
     * @param date
     *     The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     *
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     *     The attachments
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     *
     * @param attachments
     *     The attachments
     */
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    /**
     *
     * @return
     *     The replyToUser
     */
    public Integer getReplyToUser() {
        return replyToUser;
    }

    /**
     *
     * @param replyToUser
     *     The reply_to_user
     */
    public void setReplyToUser(Integer replyToUser) {
        this.replyToUser = replyToUser;
    }

    /**
     *
     * @return
     *     The replyToComment
     */
    public Integer getReplyToComment() {
        return replyToComment;
    }

    /**
     *
     * @param replyToComment
     *     The reply_to_comment
     */
    public void setReplyToComment(Integer replyToComment) {
        this.replyToComment = replyToComment;
    }



    protected WallComment(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        fromId = in.readByte() == 0x00 ? null : in.readInt();
        date = in.readByte() == 0x00 ? null : in.readInt();
        text = in.readString();
        if (in.readByte() == 0x01) {
            attachments = new ArrayList<Attachment>();
            in.readList(attachments, Attachment.class.getClassLoader());
        } else {
            attachments = null;
        }
        replyToUser = in.readByte() == 0x00 ? null : in.readInt();
        replyToComment = in.readByte() == 0x00 ? null : in.readInt();
        likes = (Likes) in.readValue(Likes.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        if (fromId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(fromId);
        }
        if (date == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(date);
        }
        dest.writeString(text);
        if (attachments == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(attachments);
        }
        if (replyToUser == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(replyToUser);
        }
        if (replyToComment == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(replyToComment);
        }
        dest.writeValue(likes);
    }

    public int getPhotoAmount(){
        int amount = 0 ;


        if(attachments!=null) {

            for (int i = 0; i < getAttachments().size(); i++) {

                if (getAttachments().get(i).getType().equals("photo")) {

                    amount++;
                }

            }
        }
        return  amount;
    }

    @SuppressWarnings("unused")
    public static final Creator<WallComment> CREATOR = new Creator<WallComment>() {
        @Override
        public WallComment createFromParcel(Parcel in) {
            return new WallComment(in);
        }

        @Override
        public WallComment[] newArray(int size) {
            return new WallComment[size];
        }
    };
}
