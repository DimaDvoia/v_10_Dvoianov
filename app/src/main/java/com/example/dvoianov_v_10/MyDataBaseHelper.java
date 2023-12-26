package com.example.dvoianov_v_10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";


    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_CREATE = "create table " +
            TABLE_USERS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_LOGIN + " text not null, " +
            COLUMN_PASSWORD + " text not null);";

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
