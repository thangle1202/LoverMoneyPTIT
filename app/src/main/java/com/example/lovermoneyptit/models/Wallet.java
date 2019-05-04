package com.example.lovermoneyptit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nguye on 3/20/2019.
 */

public class Wallet implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("walletName")
    @Expose
    private String walletName;
    @SerializedName("balance")
    @Expose
    private Double balance; // ngan sach cua vi
    @SerializedName("desc")
    @Expose
    private String desc;

    public Wallet() {
    }

    public Wallet(String walletName, Double balance, String desc) {
        this.walletName = walletName;
        this.balance = balance;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
