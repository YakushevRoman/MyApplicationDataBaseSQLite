package com.example.user301.myapplicationdatabasesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "SQLite_db_name";
    public static final int DB_VERSION = 1;
    public static final SQLiteDatabase.CursorFactory CURSOR_FACTORY = null;

    public static final String ID_COLUMS = "_id";
    public static final String NAME_COLUMS = "name";
    public static final String EMAIL_COLUMS = "email";

    public DBHelper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mySqliteDataBase ("
                + ID_COLUMS + " integer primary key autoincrement,"
                + NAME_COLUMS + " text,"
                + EMAIL_COLUMS + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
