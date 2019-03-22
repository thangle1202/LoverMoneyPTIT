package com.example.lovermoneyptit.repository;

import com.example.lovermoneyptit.models.Wallet;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by nguye on 3/21/2019.
 */

public class RealmManager {

    /**
     * clazz: VD: Wallet.class
     */
    public static RealmResults getAll(Class clazz) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(clazz).findAllAsync();
    }

    /*
    * fieldName: ten thuoc tinh cua object
    * expectFieldName: keyword de lay ra object
    * clazz: VD: Wallet.class
    * */
    public static Object getByFieldName(String fieldName, String expectFieldName, Class clazz) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(clazz).equalTo(fieldName, expectFieldName).findFirst();
    }

    /*
    * realmObject: doi tuong muon them
    * clazz: VD: Wallet.class
    * */
    public static void addItem(final RealmObject realmObject, Class clazz){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(realmObject);
            }
        });
    }

    public static void deleteItem(final String fieldName, final String expectFieldName, final Class clazz){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Object obj = getByFieldName(fieldName, expectFieldName, clazz);
            }
        });
    }

    /**
     * id tu dong tang
     * clazz: VD: Wallet.class
     */
    public static long getAndIncrementId(Class clazz) {
        Realm realm = Realm.getDefaultInstance();
        Number maxValue = realm.where(clazz).max("id");
        int value = Integer.parseInt(maxValue.toString());
        long pk = (maxValue != null) ? value + 1 : 0;
        return pk;
    }

}
