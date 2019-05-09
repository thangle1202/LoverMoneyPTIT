package com.example.lovermoneyptit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nguye on 3/20/2019.
 */

public class Group implements Serializable {

    @SerializedName("idGroupLocal")
    @Expose
    private int id;
    @SerializedName("groupName")
    @Expose
    private String groupName;
    @SerializedName("groupImage")
    @Expose
    private String image; // anh dai dien cua nhom giao dich
    @SerializedName("groupType")
    @Expose
    private int groupType; // loai nhom

    public Group() {
    }

    public Group(String groupName, int groupType) {
        this.groupName = groupName;
        this.groupType = groupType;
    }

    public Group(int id, String groupName, String image, int groupType) {
        this.id = id;
        this.groupName = groupName;
        this.image = image;
        this.groupType = groupType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }
}
