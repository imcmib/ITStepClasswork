package org.itstep.android.lesson_39_db;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/*
 * TestTable.java
 *
 * Created by aivanchenko on 22.04.2015, 14:36
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SecondTable {

    public static final String TABLE_NAME = "second";

    public static final String COLUMN_TEXT = "name";


    public static void createTable(final SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + BaseColumns._ID + " integer PRIMARY KEY AUTOINCREMENT"
                + " ," + COLUMN_TEXT + " text"
                + ");");
    }

    public static void upgradeTable(final SQLiteDatabase db,
                                    final int oldVersion, final int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        createTable(db);
    }
}