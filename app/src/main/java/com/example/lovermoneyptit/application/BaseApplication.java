package com.example.lovermoneyptit.application;

import android.app.Application;

import com.example.lovermoneyptit.repository.WalletRepo;

/**
 * Created by nguye on 3/21/2019.
 */

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        WalletRepo walletRepo = new WalletRepo(getApplicationContext());
        walletRepo.initGroup();
        walletRepo.initDeal();
        walletRepo.init();
    }
}
