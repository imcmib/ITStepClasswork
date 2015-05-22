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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.AbsListView;
import android.widget.EditText;
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
    public static final String KEY_MASK = "KEY_MASK";

    private final Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            final Bundle args = new Bundle();
            args.putString(KEY_MASK, mEditText.getText().toString());

            getSupportLoaderManager().restartLoader(LOADER_ID, args, PhoneBookActivity.this);
        }
    };

    private SimpleCursorAdapter mCursorAdapter;
    private MyObserver mObserver;
    private EditText mEditText;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, PhoneBookActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
                mHandler.removeCallbacks(mRunnable);
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                // Ignore
            }

            @Override
            public void afterTextChanged(final Editable s) {
                mHandler.postDelayed(mRunnable, 1000);
            }
        });

        mObserver = new MyObserver(new Handler());

        final String[] from = {
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        };

        final int[] to = { R.id.name_text_view };

        mCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.list_item_contect,
                null,
                from,
                to,
                0);

        final AbsListView wordList = (ListView) findViewById(R.id.list_view);
        wordList.setAdapter(mCursorAdapter);

        getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true,
                mObserver);
    }


    @Override
    protected void onResume() {
        super.onResume();

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mObserver);
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        final String mask;
        if (args != null) {
            mask = args.getString(KEY_MASK);
        } else {
            mask = null;
        }
        return new MyCursorLoader(this, mask);
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
        private final String mMask;

        public MyCursorLoader(Context context, final String mask) {
            super(context);

            mContext = context;
            mMask = mask;
        }

        @Override
        public Cursor loadInBackground() {
            final String selection;
            final String[] selectionArgs;
            if (TextUtils.isEmpty(mMask)) {
                selection = null;
                selectionArgs = null;
            } else {
                selection = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY +
                        " LIKE '%" + mMask + "%'";
                selectionArgs = null;
            }

            return mContext.getContentResolver()
                           .query(ContactsContract.Contacts.CONTENT_URI, mProjection,
                                   selection, selectionArgs, null);
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