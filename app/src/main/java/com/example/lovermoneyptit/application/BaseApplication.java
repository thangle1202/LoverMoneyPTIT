package com.example.lovermoneyptit.application;

import android.app.Application;

import com.example.lovermoneyptit.repository.WalletRepo;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nguye on 3/21/2019.
 */

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // config realm
//        Realm.init(this);
//        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
//        Realm.setDefaultConfiguration(configuration);
        WalletRepo walletRepo = new WalletRepo(getApplicationContext());
        walletRepo.initGroup();
        walletRepo.initDeal();
        walletRepo.init();
    }
}
