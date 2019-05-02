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
    private Integer id;
    @SerializedName("value")
    @Expose
    private Long value;
    private Integer idWallet;
    private Integer idGroup;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("description")
    @Expose
    private String desc;
    @SerializedName("walletDTO")
    @Expose
    private Wallet wallet;
    @SerializedName("groupDTO")
    @Expose
    private Group group;

    public Deal() {
    }

    public Deal(Long value, Integer idGroup, Integer idWallet, String createdDate, String desc, Integer userId) {
        this.value = value;
        this.idGroup = idGroup;
        this.idWallet = idWallet;
        this.createdDate = createdDate;
        this.desc = desc;
        this.userId = userId;
    }

    public Deal(Integer id, Long value, Integer idGroup, Integer idWallet, String createdDate, String desc) {
        this.id = id;
        this.value = value;
        this.idGroup = idGroup;
        this.idWallet = idWallet;
        this.createdDate = createdDate;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(Integer idWallet) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
