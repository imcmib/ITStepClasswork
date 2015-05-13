package org.itstep.android.lesson_41;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/*
 * MyProvider.java
 *
 * Created by mib on 13.05.15, 19:19
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(final Uri uri, final String[] strings, final String s, final String[] strings1, final String s1) {
        return null;
    }

    @Override
    public String getType(final Uri uri) {
        return null;
    }

    @Override
    public Uri insert(final Uri uri, final ContentValues contentValues) {

        return null;
    }

    @Override
    public int delete(final Uri uri, final String s, final String[] strings) {
        return 0;
    }

    @Override
    public int update(final Uri uri, final ContentValues contentValues, final String s, final String[] strings) {
        return 0;
    }
}
