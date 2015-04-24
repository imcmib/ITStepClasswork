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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

public class CursorLoaderActivity extends ActionBarActivity
        implements View.OnClickListener {

    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
            return new MyCursorLoader(CursorLoaderActivity.this);
        }

        @Override
        public void onLoadFinished(final Loader<Cursor> loader, final Cursor cursor) {
            mAdapter.swapCursor(cursor);
        }

        @Override
        public void onLoaderReset(final Loader<Cursor> loader) {
            // Ignore
        }
    };

    private static final String TAG = CursorLoaderActivity.class.getSimpleName();

    public static final int CURSOR_LOADER_ID = 0;

    private SimpleCursorAdapter mAdapter;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, CursorLoaderActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_loader);

        final String[] from = new String[] { TestTable.COLUMN_TEXT, TestTable.COLUMN_NUMBER };
        final int[] to = new int[] { R.id.text_view, R.id.progress_bar };

        mAdapter = new SimpleCursorAdapter(this, R.layout.list_item, null, from, to, 0);
        mAdapter.setViewBinder(new MyViewBinder());

        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view,
                                    final int position, final long id) {
                final Cursor cursor = (Cursor) mAdapter.getItem(position);
                final int numberColumnIndex = cursor.getColumnIndex(TestTable.COLUMN_NUMBER);
                final int number = cursor.getInt(numberColumnIndex);

                Toast.makeText(CursorLoaderActivity.this, String.valueOf(number), Toast.LENGTH_LONG)
                     .show();
            }
        });

        findViewById(R.id.add_button).setOnClickListener(this);

        getSupportLoaderManager().initLoader(CURSOR_LOADER_ID, null, mCallbacks);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.add_button:
                addRecord();
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: "
                        + getResources().getResourceName(view.getId()));
                break;
        }
    }

    private void addRecord() {
        final ContentValues data = new ContentValues();
        data.put(TestTable.COLUMN_NUMBER, new Random().nextInt(100));

        DBHelper.getInstance()
                .getDatabase()
                .insert(TestTable.TABLE_NAME, null, data);

        getSupportLoaderManager().restartLoader(CURSOR_LOADER_ID, null, mCallbacks);
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

    private static class MyViewBinder implements SimpleCursorAdapter.ViewBinder {
        @Override
        public boolean setViewValue(final View view, final Cursor cursor, final int columnIndex) {
            if (view instanceof ProgressBar) {
                if (cursor.getType(columnIndex) == Cursor.FIELD_TYPE_INTEGER) {
                    final int value = cursor.getInt(columnIndex);
                    ((ProgressBar) view).setProgress(value);
                    return true;
                }
            }
            return false;
        }
    }
}