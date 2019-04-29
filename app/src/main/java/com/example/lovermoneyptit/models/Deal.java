package com.example.lovermoneyptit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nguye on 3/20/2019.
 */

public class Deal implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("value")
    @Expose
    private long value; // so tien da giao dich
    @SerializedName("idGroup")
    @Expose
    private int idGroup;
    @SerializedName("idWallet")
    @Expose
    private int idWallet;
    @SerializedName("idCreatedDate")
    @Expose
    private String createdDate; // ngay giao dich
    private String desc;
    private int dealType; // loai giao dich: thu tien or chi tien
    private int userId;

    public Deal() {
    }

    public Deal(long value, int idGroup, int idWallet, String createdDate, String desc, int userId) {
        this.value = value;
        this.idGroup = idGroup;
        this.idWallet = idWallet;
        this.createdDate = createdDate;
        this.desc = desc;
        this.userId = userId;
    }

    public Deal(int id, long value, int idGroup, int idWallet, String createdDate, String desc, int dealType) {
        this.id = id;
        this.value = value;
        this.idGroup = idGroup;
        this.idWallet = idWallet;
        this.createdDate = createdDate;
        this.desc = desc;
        this.dealType = dealType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public int getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDealType() {
        return dealType;
    }

    public void setDealType(int dealType) {
        this.dealType = dealType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
