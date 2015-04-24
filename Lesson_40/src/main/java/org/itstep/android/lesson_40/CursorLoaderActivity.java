package org.itstep.android.lesson_40;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.Random;

public class CursorLoaderActivity extends ActionBarActivity
        implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    private static final String TAG = CursorLoaderActivity.class.getSimpleName();

    private SimpleCursorAdapter mAdapter;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, CursorLoaderActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_loader);

        final String[] from = new String[] { TestTable.COLUMN_NUMBER };
        final int[] to = new int[] { R.id.text_view };

        mAdapter = new SimpleCursorAdapter(this, R.layout.list_item, null, from, to, 0);

        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        findViewById(R.id.add_button).setOnClickListener(this);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        return new MyCursorLoader(this);
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
        // Ignore
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.add_button:
                addRecord();
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }

    private void addRecord() {
        final ContentValues data = new ContentValues();
        data.put(TestTable.COLUMN_NUMBER, new Random().nextInt(100));

        DBHelper.getInstance().getDatabase().insert(TestTable.TABLE_NAME, null, data);

        getSupportLoaderManager().restartLoader(0, null, this);
    }

    private static class MyCursorLoader extends CursorLoader {

        public MyCursorLoader(Context context) {
            super(context);
        }

        @Override
        public Cursor loadInBackground() {
            return DBHelper.getInstance().getDatabase()
                           .query(TestTable.TABLE_NAME, // table name
                                   null, // columns
                                   null, // selection
                                   null, // selectionArgs
                                   null, // groupBy
                                   null, // having
                                   null);// orderBy
        }
    }
}