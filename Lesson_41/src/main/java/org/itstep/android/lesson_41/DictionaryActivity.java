package org.itstep.android.lesson_41;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.UserDictionary;
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
 * DictionaryActivity.java
 *
 * Created by mib on 13.05.15, 15:57
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class DictionaryActivity extends ActionBarActivity
        implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] mProjection = {
            UserDictionary.Words._ID,
            UserDictionary.Words.WORD,
            UserDictionary.Words.LOCALE
    };

    private static final String TAG = DictionaryActivity.class.getSimpleName();

    private static final int LOADER_ID = 1;

    private SimpleCursorAdapter mCursorAdapter;
    private EditText mWordEditText;
    private MyObserver mObserver;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, DictionaryActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        mObserver = new MyObserver(new Handler());

        final String[] mWordListColumns = {
                UserDictionary.Words._ID,
                UserDictionary.Words.WORD,
                UserDictionary.Words.LOCALE
        };

        final int[] mWordListItems = { R.id.id, R.id.word, R.id.locale};

        mCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.list_item_word,
                null,
                mWordListColumns,
                mWordListItems,
                0);

        final AbsListView wordList = (ListView) findViewById(R.id.list_view);
        wordList.setAdapter(mCursorAdapter);

        mWordEditText = (EditText) findViewById(R.id.word_edit_text);
        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.update_button).setOnClickListener(this);
        findViewById(R.id.delete_button).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getContentResolver().registerContentObserver(UserDictionary.Words.CONTENT_URI, true,
                mObserver);

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
        final String word = mWordEditText.getText().toString();

        final ContentValues mNewValues = new ContentValues();
        mNewValues.put(UserDictionary.Words.APP_ID, "example.user");
        mNewValues.put(UserDictionary.Words.LOCALE, "en_US");
        mNewValues.put(UserDictionary.Words.WORD, word);
        mNewValues.put(UserDictionary.Words.FREQUENCY, "100");

        final Uri uri = getContentResolver().insert(
                UserDictionary.Words.CONTENT_URI,
                mNewValues
        );

        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
    }

    private void updateWord() {
        final String id = mWordEditText.getText().toString();

        final ContentValues mNewValues = new ContentValues();
        mNewValues.put(UserDictionary.Words.WORD, "UPDATED");

        final int count = getContentResolver().update(UserDictionary.Words.CONTENT_URI, mNewValues,
                UserDictionary.Words._ID + " = ?", new String[]{id});

        Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
    }

    private void deleteWord() {
        final String id = mWordEditText.getText().toString();

        final int count = getContentResolver()
                .delete(UserDictionary.Words.CONTENT_URI, UserDictionary.Words._ID + " = ?",
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
                           .query(UserDictionary.Words.CONTENT_URI,
                                   mProjection,
                                   null, null, null);
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
            getSupportLoaderManager().restartLoader(LOADER_ID, null, DictionaryActivity.this);
        }
    }
}