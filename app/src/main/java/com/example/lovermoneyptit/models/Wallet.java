package com.example.lovermoneyptit.models;

import java.io.Serializable;

/**
 * Created by nguye on 3/20/2019.
 */

public class Wallet implements Serializable {

    private int id;
    private String walletName;
    private Double balance; // ngan sach cua vi
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
