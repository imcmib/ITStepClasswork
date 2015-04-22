package org.itstep.android.lesson_39_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test_db.db";
    public static final int DATABASE_VERSION = 2;

    private static volatile DBHelper sInstance = null;

    private final SQLiteDatabase mDatabase;

    public static DBHelper getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("init not called");
        }
        return sInstance;
    }

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (DBHelper.class) {
                if (sInstance == null) {
                    sInstance = new DBHelper(context);
                }
            }
        }
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TestTable.createTable(db);
        SecondTable.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TestTable.upgradeTable(db, oldVersion, newVersion);
        SecondTable.upgradeTable(db, oldVersion, newVersion);
    }

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }
}