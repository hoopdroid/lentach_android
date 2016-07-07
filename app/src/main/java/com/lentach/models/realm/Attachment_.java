
package com.lentach.models.realm;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Attachment_ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private Photo_ photo;
    @SerializedName("audio")
    @Expose
    private Audio audio;

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
    public Photo_ getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     *     The photo
     */
    public void setPhoto(Photo_ photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     *     The audio
     */
    public Audio getAudio() {
        return audio;
    }

    /**
     *
     * @param audio
     *     The audio
     */
    public void setAudio(Audio audio) {
        this.audio = audio;
    }

}
