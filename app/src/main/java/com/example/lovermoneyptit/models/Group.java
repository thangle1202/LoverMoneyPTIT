package com.example.lovermoneyptit.models;

import java.io.Serializable;

/**
 * Created by nguye on 3/20/2019.
 */

public class Group implements Serializable {
    private int id;
    private String groupName;
    private String image; // anh dai dien cua nhom giao dich

    public Group() {
    }

    public Group(int id, String groupName, String image) {
        this.id = id;
        this.groupName = groupName;
        this.image = image;
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
}