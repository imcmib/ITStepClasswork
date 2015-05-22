package org.itstep.android.lesson_40;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.widget.AbsListView;
import android.widget.ListView;

/*
 * ContentProviderActivity.java
 *
 * Created by aivanchenko on 22.05.2015, 14:07
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class PhoneBookActivity extends ActionBarActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] mProjection = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    };

    private static final int LOADER_ID = 1;

    private SimpleCursorAdapter mCursorAdapter;
    private MyObserver mObserver;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, PhoneBookActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);

        mObserver = new MyObserver(new Handler());

        final String[] selection = {
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        };

        final int[] selectionArgs = { R.id.name_text_view };

        mCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.list_item_contect,
                null,
                selection,
                selectionArgs,
                0);

        final AbsListView wordList = (ListView) findViewById(R.id.list_view);
        wordList.setAdapter(mCursorAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        getContentResolver().registerContentObserver(
                ContactsContract.Contacts.CONTENT_URI, true, mObserver);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getContentResolver().unregisterContentObserver(mObserver);
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        return new MyCursorLoader(this);
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
        // Ignore
    }

    private static class MyCursorLoader extends CursorLoader {

        private final Context mContext;

        public MyCursorLoader(Context context) {
            super(context);

            mContext = context;
        }

        @Override
        public Cursor loadInBackground() {
            return mContext.getContentResolver()
                           .query(ContactsContract.Contacts.CONTENT_URI, mProjection, null, null, null);
        }
    }

    private class MyObserver extends ContentObserver {

        public MyObserver(final Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(final boolean selfChange) {
            this.onChange(selfChange, null);
        }

        @Override
        public void onChange(final boolean selfChange, final Uri uri) {
            getSupportLoaderManager().restartLoader(LOADER_ID, null, PhoneBookActivity.this);
        }
    }
}