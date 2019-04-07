package com.example.lovermoneyptit.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lovermoneyptit.models.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletRepo extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MONEY_LOVER";
    private static final String TABLE_WALLET = "WALLET";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_BALANCE = "BALANCE";
    private static final String COLUMN_DESC = "DESC";

    public WalletRepo(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "WalletRepo.onCreate ... ");
        // create table wallet
        String script = "CREATE TABLE " + TABLE_WALLET + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"
                + COLUMN_BALANCE + " REAL," + COLUMN_DESC + " TEXT)";
        sqLiteDatabase.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Log.i(TAG, "WalletRepo.onUpgrade ... ");

        // drop table if exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WALLET);

        // re-create table
        onCreate(sqLiteDatabase);

    }

    // init
    public void init(){

        int quantity = this.getWalletsQuantity();
        if(quantity == 0) {

            Wallet wallet1 = new Wallet("Wallet 1", 12345.6d, "mo ta wallet 1");
            Wallet wallet2 = new Wallet("Wallet 2", 6789.9d, "mo ta wallet 2");

            this.addWallet(wallet1);
            this.addWallet(wallet2);
        }
    }

    public void addWallet(Wallet wallet){

        Log.i(TAG, "WalletRepo.addWallet ... " + wallet.getWalletName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, wallet.getWalletName());
        contentValues.put(COLUMN_BALANCE, wallet.getBalance());
        contentValues.put(COLUMN_DESC, wallet.getDesc());

        // insert into table
        db.insert(TABLE_WALLET, null, contentValues);

        // close connection
        db.close();

    }

    public Wallet findWalletById(int id){

        Log.i(TAG, "WalletRepo.findWalletById ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WALLET, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_BALANCE, COLUMN_DESC},
                        COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                        null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Wallet wallet = new Wallet();
        wallet.setId(cursor.getInt(0));
        wallet.setWalletName(cursor.getString(1));
        wallet.setBalance(cursor.getDouble(2));
        wallet.setDesc(cursor.getString(3));

        db.close();

        return wallet;

    }

    public List<Wallet> getAllWallets(){

        Log.i(TAG, "WalletRepo.getAllWallets ... " );

        List<Wallet> wallets = new ArrayList<>();
        // select wallets from db
        String query = "SELECT * FROM " + TABLE_WALLET;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do {
                Wallet wallet = new Wallet();
                wallet.setId(cursor.getInt(0));
                wallet.setWalletName(cursor.getString(1));
                wallet.setBalance(cursor.getDouble(2));
                wallet.setDesc(cursor.getString(3));
                wallets.add(wallet);
            } while(cursor.moveToNext());

        }

        db.close();

        return wallets;

    }

    public int getWalletsQuantity(){

        Log.i(TAG, "WalletRepo.getWalletsQuantity ... " );

        String query = "SELECT * FROM " + TABLE_WALLET;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        int result = cursor.getCount();

        db.close();
        cursor.close();

        return result;

    }

    public int updateWallet(Wallet wallet){

        Log.i(TAG, "WalletRepo.updateWallet ... " + wallet.getWalletName() );

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, wallet.getWalletName());
        values.put(COLUMN_BALANCE, wallet.getBalance());
        values.put(COLUMN_DESC, wallet.getDesc());

        int result =  db.update(TABLE_WALLET, values, COLUMN_ID + " = ?",
                        new String[]{String.valueOf(wallet.getId())});

        //db.close();

        return result;

    }

    public void deleteWallet(Wallet wallet){

        Log.i(TAG, "WalletRepo.deleteWallet ... " + wallet.getWalletName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_WALLET, COLUMN_ID + " =?",
                    new String[]{String.valueOf(wallet.getId())});
        db.close();

    }

}
