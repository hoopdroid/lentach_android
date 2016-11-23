
package com.lentach.repository.realmmodel;


import io.realm.RealmObject;


public class Page extends RealmObject {


    private Integer id;

    private Integer groupId;

    private String title;

    private Integer whoCanView;

    private Integer whoCanEdit;

    private Integer edited;

    private Integer created;

    private Integer views;

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



}