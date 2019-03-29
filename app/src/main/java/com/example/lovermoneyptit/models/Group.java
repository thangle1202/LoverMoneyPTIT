package com.example.lovermoneyptit.models;

import java.io.Serializable;

/**
 * Created by nguye on 3/20/2019.
 */

public class Group implements Serializable {
    private static int id;
    private String groupName;
    private int image; // anh dai dien cua nhom giao dich

    public Group() {
    }

    public Group(String groupName, int image) {
        this.groupName = groupName;
        this.image = image;
    }

    public Group(int id, String groupName, int image) {
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
