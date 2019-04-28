package com.example.lovermoneyptit.models;

import java.io.Serializable;

public class Debt implements Serializable {
    private int id;
    private long value; // so tien da giao dich
    private int idWallet;
    private String createdDate; // ngay giao dich
    private String desc;
    private String personName; //nguoi vay/ no
    private int dealType; // loai giao dich: thu tien or chi tien

    public Debt() {
    }

    public Debt(int id, long value, int idWallet, String createdDate, String desc, String personName, int dealType) {
        this.id = id;
        this.value = value;
        this.idWallet = idWallet;
        this.createdDate = createdDate;
        this.desc = desc;
        this.personName = personName;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getDealType() {
        return dealType;
    }

    public void setDealType(int dealType) {
        this.dealType = dealType;
    }
}
