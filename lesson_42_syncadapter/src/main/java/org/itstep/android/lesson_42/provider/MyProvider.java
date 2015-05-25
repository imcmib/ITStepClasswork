package org.itstep.android.lesson_42.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

import org.itstep.android.lesson_42.BuildConfig;

/*
 * MyProvider.java
 *
 * Created by aivanchenko on 22.05.2015, 14:11
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyProvider extends ContentProvider {

    private static final String DB_NAME = "my_database";
    private static final int DB_VERSION = 1;

    public static final String CONTACT_TABLE = "contacts";

    public static final String CONTACT_ID = "_id";
    public static final String CONTACT_NAME = "name";
    public static final String CONTACT_EMAIL = "email";

    private static final String DB_CREATE = "CREATE TABLE " + CONTACT_TABLE + "("
            + CONTACT_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + CONTACT_NAME + " text, "
            + CONTACT_EMAIL + " text" + ");";

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider.MyProvider";

    private static final String CONTACT_PATH = "contacts";

    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + CONTACT_PATH);

    private static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    private static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    private static final int URI_CONTACTS = 1;
    private static final int URI_CONTACTS_ID = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_CONTACTS);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_CONTACTS_ID);
    }

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs,
                        String sortOrder) {

        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = CONTACT_ID + " ASC";
                }
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(CONTACT_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), CONTACT_CONTENT_URI);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != URI_CONTACTS) {
            throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();

        long rowID = db.insert(CONTACT_TABLE, null, values);

        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);

        getContext().getContentResolver().notifyChange(resultUri, null);

        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                // Ignore
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();

        int cnt = db.delete(CONTACT_TABLE, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return cnt;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                // Ignore
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();

        int cnt = db.update(CONTACT_TABLE, values, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return cnt;
    }

    @Override
    public String getType(final Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                return CONTACT_CONTENT_TYPE;
            case URI_CONTACTS_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // todo
        }
    }
}