package com.example.lovermoneyptit.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.utils.GroupType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WalletRepo extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MONEY_LOVER";
    // wallet
    private static final String TABLE_WALLET = "WALLET";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_BALANCE = "BALANCE";
    private static final String COLUMN_DESC = "DESC";

    //group
    private static final String TABLE_GROUP = "group_deal";
    private static final String COLUMN_GROUP_ID = "id";
    private static final String COLUMN_GROUP_NAME = "group_name";
    private static final String COLUMN_GROUP_IMAGE = "group_image";
    private static final String COLUMN_GROUP_TYPE = "group_type";

    //deal
    private static final String TABLE_DEAL = "deal";
    private static final String COLUMN_DEAL_ID = "id";
    private static final String COLUMN_DEAL_VALUE = "value";
    private static final String COLUMN_DEAL_ID_WALLET = "id_wallet";
    private static final String COLUMN_DEAL_ID_GROUP = "id_group";
    private static final String COLUMN_DEAL_CREATED_DATE = "created_date";
    private static final String COLUMN_DEAL_DESC = "description";

    // create table
    private static final String CREATE_TABLE_WALLET = "CREATE TABLE IF NOT EXISTS " + TABLE_WALLET + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"
            + COLUMN_BALANCE + " REAL, " + COLUMN_DESC + " TEXT)";

    private static final String CREATE_TABLE_GROUP = "CREATE TABLE IF NOT EXISTS " + TABLE_GROUP + "("
            + COLUMN_GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_GROUP_NAME + " VARCHAR(45),"
            + COLUMN_GROUP_IMAGE + " VARCHAR(45)," + COLUMN_GROUP_TYPE + " INTEGER)";

    private static final String CREATE_TABLE_DEAL = "CREATE TABLE IF NOT EXISTS " + TABLE_DEAL + "("
            + COLUMN_DEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_DEAL_VALUE + " LONG,"
            + COLUMN_DEAL_ID_WALLET + " INTEGER," + COLUMN_DEAL_ID_GROUP + " INTEGER, " + COLUMN_DEAL_CREATED_DATE + " TEXT, "
            + COLUMN_DEAL_DESC + " VARCHAR(45))";

    public WalletRepo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "WalletRepo.onCreate ... ");

        sqLiteDatabase.execSQL(CREATE_TABLE_WALLET);
        sqLiteDatabase.execSQL(CREATE_TABLE_GROUP);
        sqLiteDatabase.execSQL(CREATE_TABLE_DEAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Log.i(TAG, "WalletRepo.onUpgrade ... ");

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WALLET);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DEAL);

        onCreate(sqLiteDatabase);
    }

    public void init() {

        int quantity = this.getWalletsQuantity();
        if (quantity == 0) {

            Wallet wallet1 = new Wallet("Wallet 1", 12345.6d, "mo ta wallet 1");
            Wallet wallet2 = new Wallet("Wallet 2", 6789.9d, "mo ta wallet 2");

            this.addWallet(wallet1);
            this.addWallet(wallet2);
        }
    }

    public void initGroup() {
        if (this.getGroupQuantity() == 0) {
            Group group1 = new Group("Group 1", GroupType.CASH_OUT);
            Group group2 = new Group("Group 2", GroupType.CASH_IN);
            Group group3 = new Group("Group 3", GroupType.LOAN);

            this.addGroup(group1);
            this.addGroup(group2);
            this.addGroup(group3);
        }
    }

    public void initDeal() {
        if (this.getDealQuantity() == 0) {
            Deal deal1 = new Deal(1, 1, 1, "24/04/2019", "deal mac dinh");
            Deal deal2 = new Deal(2, 1, 2, "24/04/2019", "deal mac dinh");

            this.addDeal(deal1);
            this.addDeal(deal2);
        }
    }

    // wallet
    public void addWallet(Wallet wallet) {

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

    public Wallet findWalletById(int id) {

        Log.i(TAG, "WalletRepo.findWalletById ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WALLET, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_BALANCE, COLUMN_DESC},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Wallet wallet = new Wallet();
        wallet.setId(cursor.getInt(0));
        wallet.setWalletName(cursor.getString(1));
        wallet.setBalance(cursor.getDouble(2));
        wallet.setDesc(cursor.getString(3));

        db.close();

        return wallet;

    }

    public List<Wallet> getAllWallets() {

        Log.i(TAG, "WalletRepo.getAllWallets ... ");

        List<Wallet> wallets = new ArrayList<>();
        // select wallets from db
        String query = "SELECT * FROM " + TABLE_WALLET;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            do {
                Wallet wallet = new Wallet();
                wallet.setId(cursor.getInt(0));
                wallet.setWalletName(cursor.getString(1));
                wallet.setBalance(cursor.getDouble(2));
                wallet.setDesc(cursor.getString(3));
                wallets.add(wallet);
            } while (cursor.moveToNext());

        }

        db.close();

        return wallets;

    }

    public int getWalletsQuantity() {

        Log.i(TAG, "WalletRepo.getWalletsQuantity ... ");

        String query = "SELECT * FROM " + TABLE_WALLET;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        int result = cursor.getCount();

        db.close();
        cursor.close();

        return result;

    }

    public int updateWallet(Wallet wallet) {

        Log.i(TAG, "WalletRepo.updateWallet ... " + wallet.getWalletName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, wallet.getWalletName());
        values.put(COLUMN_BALANCE, wallet.getBalance());
        values.put(COLUMN_DESC, wallet.getDesc());

        int result = db.update(TABLE_WALLET, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(wallet.getId())});

        //db.close();
        return result;
    }

    public int updateBalanceWallet(Wallet wallet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BALANCE, wallet.getBalance());

        int result = db.update(TABLE_WALLET, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(wallet.getId())});

        //db.close();
        return result;
    }

    public void deleteWallet(Wallet wallet) {

        Log.i(TAG, "WalletRepo.deleteWallet ... " + wallet.getWalletName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_WALLET, COLUMN_ID + " =?",
                new String[]{String.valueOf(wallet.getId())});
        db.close();
    }

    // group
    public void addGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_GROUP_NAME, group.getGroupName());
        contentValues.put(COLUMN_GROUP_TYPE, group.getGroupType());

        db.insert(TABLE_GROUP, null, contentValues);
        db.close();
    }

    public int getGroupQuantity() {

        Log.i(TAG, "WalletRepo.getGroupQuantity ... ");

        String query = "SELECT * FROM " + TABLE_GROUP;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        int result = cursor.getCount();

        db.close();
        cursor.close();

        return result;
    }

    public List<Group> getAllGroup() {
        List<Group> groups = new ArrayList<Group>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_GROUP;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Group group = new Group();
            group.setId(cursor.getInt(0));
            group.setGroupName(cursor.getString(1));
            if (cursor.getString(2) != null) {
                group.setImage(cursor.getString(2));
            }
            group.setGroupType(cursor.getInt(3));

            groups.add(group);
        }
        db.close();
        return groups;
    }

    public List<Group> getGroupByType(int groupType) {
        List<Group> groups = new ArrayList<Group>();
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_GROUP + " WHERE group_type=" + groupType;
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Group group = new Group();
            group.setId(cursor.getInt(0));
            group.setGroupName(cursor.getString(1));
            if (cursor.getString(2) != null) {
                group.setImage(cursor.getString(2));
            }
            group.setGroupType(cursor.getInt(3));

            groups.add(group);
        }
        database.close();
        return groups;
    }

    public Group getGroupById(int groupId) {
        Group group = new Group();
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_GROUP + " WHERE id=" + groupId;
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            group.setId(cursor.getInt(0));
            group.setGroupName(cursor.getString(1));
            if (cursor.getString(2) != null) {
                group.setImage(cursor.getString(2));
            }
            group.setGroupType(cursor.getInt(3));
        }
        database.close();
        return group;
    }

    public int editGroup(Group group) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_GROUP_NAME, group.getGroupName());
        values.put(COLUMN_GROUP_IMAGE, group.getImage());
        values.put(COLUMN_GROUP_TYPE, group.getGroupType());

        int result = db.update(TABLE_GROUP, values, COLUMN_GROUP_ID + " = ?",
                new String[]{String.valueOf(group.getId())});

        return result;
    }

    public void deleteGroup(Group group) {
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_GROUP, COLUMN_GROUP_ID + " = ?",
                new String[]{String.valueOf(group.getId())});

        database.close();
    }

    //deal
    public void addDeal(Deal deal) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DEAL_VALUE, deal.getValue());
        contentValues.put(COLUMN_DEAL_ID_WALLET, deal.getIdWallet());
        contentValues.put(COLUMN_DEAL_ID_GROUP, deal.getIdGroup());
        contentValues.put(COLUMN_DEAL_CREATED_DATE, deal.getCreatedDate().toString());
        contentValues.put(COLUMN_DEAL_DESC, deal.getDesc());

        database.insert(TABLE_DEAL, null, contentValues);

        database.close();

    }

    public int getDealQuantity() {

        Log.i(TAG, "WalletRepo.getDealQuantity ... ");

        String query = "SELECT * FROM " + TABLE_DEAL;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        int result = cursor.getCount();

        db.close();
        cursor.close();

        return result;
    }

    public int editDeal(Deal deal) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEAL_VALUE, deal.getValue());
        values.put(COLUMN_DEAL_ID_WALLET, deal.getIdWallet());
        values.put(COLUMN_DEAL_ID_GROUP, deal.getIdGroup());
        values.put(COLUMN_DEAL_DESC, deal.getDesc());

        int result = database.update(TABLE_DEAL, values, COLUMN_DEAL_ID + " = ?",
                new String[]{String.valueOf(deal.getId())});

        return result;
    }

    public List<Deal> getAllDeal() throws ParseException {
        List<Deal> deals = new ArrayList<Deal>();
        String sql = "SELECT * FROM " + TABLE_DEAL;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Deal deal = new Deal();
            deal.setId(cursor.getInt(0));
            deal.setValue(cursor.getLong(1));
            deal.setIdWallet(cursor.getInt(2));
            deal.setIdGroup(cursor.getInt(3));
            deal.setCreatedDate(cursor.getString(4));
            deal.setDesc(cursor.getString(5));

            deals.add(deal);
        }
        return deals;
    }

    public void deleteDeal(Deal deal) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_DEAL, COLUMN_DEAL_ID + "= ? ",
                new String[]{String.valueOf(deal.getId())});
    }

    public Deal getDealById(int dealId) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_DEAL + " WHERE id=" + dealId;
        Cursor cursor = database.rawQuery(sql, null);
        Deal deal = new Deal();
        while (cursor.moveToNext()) {
            deal.setValue(cursor.getLong(1));
            deal.setIdWallet(cursor.getInt(2));
            deal.setIdGroup(cursor.getInt(3));
            deal.setCreatedDate(cursor.getString(4));
            deal.setDesc(cursor.getString(5));
        }
        return deal;
    }

}
