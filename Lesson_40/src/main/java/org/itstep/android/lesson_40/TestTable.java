package org.itstep.android.lesson_40;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/*
 * TestTable.java
 *
 * Created by aivanchenko on 22.04.2015, 14:36
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TestTable {

    public static final String TABLE_NAME = "test";

    public static final String COLUMN_TEXT = "name";
    public static final String COLUMN_NUMBER = "number";

    private static final String TAG = TestTable.class.getSimpleName();


    public static void createTable(final SQLiteDatabase db) {
        Log.i(TAG, "createTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + BaseColumns._ID + " integer PRIMARY KEY AUTOINCREMENT"
                + " ," + COLUMN_TEXT + " text"
                + " ," + COLUMN_NUMBER + " integer"
                + ");");
    }

    public static void upgradeTable(final SQLiteDatabase db,
                                    final int oldVersion, final int newVersion) {
        Log.i(TAG, "upgradeTable, old: " + oldVersion + ", new: " + newVersion);
        if (newVersion > 1) {
            updateFrom1To2(db);
        }
    }

    private static void updateFrom1To2(final SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD " + COLUMN_NUMBER + " integer");
    }
}