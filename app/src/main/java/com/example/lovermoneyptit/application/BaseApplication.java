package com.example.lovermoneyptit.application;

import android.app.Application;

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
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
