package com.example.lovermoneyptit.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nguye on 3/20/2019.
 */

public class Deal implements Serializable {
    private int id;
    private long value; // so tien da giao dich
    private int idGroup;
    private int idWallet;
    private Date createdDate; // ngay giao dich
    private String desc;
    private int dealType; // loai giao dich: thu tien or chi tien

    public Deal() {
    }

    public Deal(int id, long value, int idGroup, int idWallet, Date createdDate, String desc, int dealType) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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
}
