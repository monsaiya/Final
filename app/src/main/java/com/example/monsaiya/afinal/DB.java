package com.example.monsaiya.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Monsaiya on 10/12/2560.
 */

public class DB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "phone.db";
    private static final int DATABASE_VERSION = 8;

    public static final String TABLE_NAME = "income";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_NUMBER = "number";
    public static final String COL_MONEY = "money";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT, "
            + COL_NUMBER + " INTEGER, "
            + COL_MONEY + " INTEGER)";
    
    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        insertInitialData(db);

    }

    private void insertInitialData(SQLiteDatabase db) {

        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, "คุณพ่อให้เงิน");
        cv.put(COL_NUMBER, 1);
        cv.put(COL_MONEY, 8000);
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TITLE, "จ่ายค่าหอ");
        cv.put(COL_NUMBER, 2);
        cv.put(COL_MONEY, 2500);
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TITLE, "ซื้อล็อตเตอรี่ 1 ชุด");
        cv.put(COL_NUMBER, 2);
        cv.put(COL_MONEY, 700);
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TITLE, "ถูกล็อตเตอรี่รางวัลที่ 1");
        cv.put(COL_NUMBER, 1);
        cv.put(COL_MONEY, 30000000);
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
