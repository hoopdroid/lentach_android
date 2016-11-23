package com.lentach.repository.realmmodel;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;


public class CopyHistory extends RealmObject {


    private Integer id;

    private Integer ownerId;

    private Integer fromId;

    private Integer date;

    private String postType;

    private String text;

    private RealmList<Attachment> attachments;

    private PostSource postSource;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The ownerId
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     *
     * @param ownerId
     * The owner_id
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     *
     * @return
     * The fromId
     */
    public Integer getFromId() {
        return fromId;
    }

    /**
     *
     * @param fromId
     * The from_id
     */
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    /**
     *
     * @return
     * The date
     */
    public Integer getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The postType
     */
    public String getPostType() {
        return postType;
    }

    /**
     *
     * @param postType
     * The post_type
     */
    public void setPostType(String postType) {
        this.postType = postType;
    }

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The attachments
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     *
     * @param attachments
     * The attachments
     */
    public void setAttachments(RealmList<Attachment> attachments) {
        this.attachments = attachments;
    }

    /**
     *
     * @return
     * The postSource
     */
    public PostSource getPostSource() {
        return postSource;
    }

    /**
     *
     * @param postSource
     * The post_source
     */
    public void setPostSource(PostSource postSource) {
        this.postSource = postSource;
    }


}