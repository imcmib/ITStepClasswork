package org.itstep.android.lesson_40;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/*
 * ContentProviderActivity.java
 *
 * Created by aivanchenko on 22.05.2015, 14:07
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ContentProviderActivity extends ActionBarActivity
        implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] mProjection = {
            MyProvider.CONTACT_ID,
            MyProvider.CONTACT_NAME,
            MyProvider.CONTACT_EMAIL
    };

    private static final String TAG = ContentProviderActivity.class.getSimpleName();

    private static final int LOADER_ID = 1;

    private SimpleCursorAdapter mCursorAdapter;
    private EditText mNameEditText;
    private MyObserver mObserver;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, ContentProviderActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        mObserver = new MyObserver(new Handler());

        final String[] selection = {
                MyProvider.CONTACT_ID,
                MyProvider.CONTACT_NAME,
                MyProvider.CONTACT_EMAIL
        };

        final int[] selectionArgs = { R.id.id_text_view, R.id.name_text_view, R.id.email_text_view};

        mCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.list_item_contect,
                null,
                selection,
                selectionArgs,
                0);

        final AbsListView wordList = (ListView) findViewById(R.id.list_view);
        wordList.setAdapter(mCursorAdapter);

        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.update_button).setOnClickListener(this);
        findViewById(R.id.delete_button).setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        getContentResolver().registerContentObserver(MyProvider.CONTACT_CONTENT_URI, true, mObserver);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getContentResolver().unregisterContentObserver(mObserver);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.add_button:
                addNewWord();
                break;
            case R.id.update_button:
                updateWord();
                break;
            case R.id.delete_button:
                deleteWord();
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: "
                        + getResources().getResourceName(view.getId()));
                break;
        }
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

    private void addNewWord() {
        final String name = mNameEditText.getText().toString();

        final ContentValues mNewValues = new ContentValues();
        mNewValues.put(MyProvider.CONTACT_NAME, name);
        mNewValues.put(MyProvider.CONTACT_EMAIL, String.format("%s@mail.com", name));

        final Uri uri = getContentResolver().insert(
                MyProvider.CONTACT_CONTENT_URI,
                mNewValues
        );

        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
    }

    private void updateWord() {
        final String id = mNameEditText.getText().toString();

        final ContentValues mNewValues = new ContentValues();
        mNewValues.put(MyProvider.CONTACT_NAME, "UPDATED");

        final int count = getContentResolver().update(MyProvider.CONTACT_CONTENT_URI, mNewValues,
                MyProvider.CONTACT_ID + " = ?", new String[]{id});

        Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
    }

    private void deleteWord() {
        final String id = mNameEditText.getText().toString();

        final int count = getContentResolver()
                .delete(MyProvider.CONTACT_CONTENT_URI, MyProvider.CONTACT_ID + " = ?",
                        new String[]{id});

        Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
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
                           .query(MyProvider.CONTACT_CONTENT_URI, mProjection, null, null, null);
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
            getSupportLoaderManager().restartLoader(LOADER_ID, null, ContentProviderActivity.this);
        }
    }
}