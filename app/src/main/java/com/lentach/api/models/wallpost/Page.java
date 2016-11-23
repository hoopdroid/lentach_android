
package com.lentach.api.models.wallpost;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Page implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("who_can_view")
    @Expose
    private Integer whoCanView;
    @SerializedName("who_can_edit")
    @Expose
    private Integer whoCanEdit;
    @SerializedName("edited")
    @Expose
    private Integer edited;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("view_url")
    @Expose
    private String viewUrl;

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
     *     The groupId
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     *
     * @param groupId
     *     The group_id
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The whoCanView
     */
    public Integer getWhoCanView() {
        return whoCanView;
    }

    /**
     *
     * @param whoCanView
     *     The who_can_view
     */
    public void setWhoCanView(Integer whoCanView) {
        this.whoCanView = whoCanView;
    }

    /**
     *
     * @return
     *     The whoCanEdit
     */
    public Integer getWhoCanEdit() {
        return whoCanEdit;
    }

    /**
     *
     * @param whoCanEdit
     *     The who_can_edit
     */
    public void setWhoCanEdit(Integer whoCanEdit) {
        this.whoCanEdit = whoCanEdit;
    }

    /**
     *
     * @return
     *     The edited
     */
    public Integer getEdited() {
        return edited;
    }

    /**
     *
     * @param edited
     *     The edited
     */
    public void setEdited(Integer edited) {
        this.edited = edited;
    }

    /**
     *
     * @return
     *     The created
     */
    public Integer getCreated() {
        return created;
    }

    /**
     *
     * @param created
     *     The created
     */
    public void setCreated(Integer created) {
        this.created = created;
    }

    /**
     *
     * @return
     *     The views
     */
    public Integer getViews() {
        return views;
    }

    /**
     *
     * @param views
     *     The views
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     *
     * @return
     *     The viewUrl
     */
    public String getViewUrl() {
        return viewUrl;
    }

    /**
     *
     * @param viewUrl
     *     The view_url
     */
    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }


    protected Page(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        groupId = in.readByte() == 0x00 ? null : in.readInt();
        title = in.readString();
        whoCanView = in.readByte() == 0x00 ? null : in.readInt();
        whoCanEdit = in.readByte() == 0x00 ? null : in.readInt();
        edited = in.readByte() == 0x00 ? null : in.readInt();
        created = in.readByte() == 0x00 ? null : in.readInt();
        views = in.readByte() == 0x00 ? null : in.readInt();
        viewUrl = in.readString();
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
        if (groupId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(groupId);
        }
        dest.writeString(title);
        if (whoCanView == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(whoCanView);
        }
        if (whoCanEdit == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(whoCanEdit);
        }
        if (edited == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(edited);
        }
        if (created == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(created);
        }
        if (views == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(views);
        }
        dest.writeString(viewUrl);
    }

    @SuppressWarnings("unused")
    public static final Creator<Page> CREATOR = new Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel in) {
            return new Page(in);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };
}