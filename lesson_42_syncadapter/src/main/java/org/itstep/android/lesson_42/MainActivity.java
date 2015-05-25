package org.itstep.android.lesson_42;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncStatusObserver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import org.itstep.android.lesson_42.accounts.GenericAccountService;
import org.itstep.android.lesson_42.provider.MyProvider;
import org.itstep.android.lesson_42.sync.SyncUtils;

public class MainActivity extends ActionBarActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MainActivity";

    private static final String[] mProjection = {
            MyProvider.CONTACT_ID,
            MyProvider.CONTACT_NAME,
            MyProvider.CONTACT_EMAIL
    };

    private static final int LOADER_ID = 1;

    private SimpleCursorAdapter mCursorAdapter;
    private Object mSyncObserverHandle;
    private MyObserver mObserver;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

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

        findViewById(R.id.sync_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                SyncUtils.refresh();
            }
        });

        mObserver = new MyObserver(new Handler());

        SyncUtils.createSyncAccount(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSyncStatusObserver.onStatusChanged(0);

        final int mask = ContentResolver.SYNC_OBSERVER_TYPE_PENDING |
                ContentResolver.SYNC_OBSERVER_TYPE_ACTIVE;
        mSyncObserverHandle = ContentResolver.addStatusChangeListener(mask, mSyncStatusObserver);

        getContentResolver().registerContentObserver(MyProvider.CONTACT_CONTENT_URI, true, mObserver);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        getContentResolver().unregisterContentObserver(mObserver);

        if (mSyncObserverHandle != null) {
            ContentResolver.removeStatusChangeListener(mSyncObserverHandle);
            mSyncObserverHandle = null;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        if (id == LOADER_ID) {
            return new MyCursorLoader(this);
        }
        return null;
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
        // Ignore
    }

    @Override
    public void onRefresh() {
        SyncUtils.refresh();
    }

    public void setRefreshState(boolean refreshing) {
        Log.i(TAG, "setRefreshState: " + refreshing);

        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(refreshing);
        }
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
            getSupportLoaderManager().restartLoader(LOADER_ID, null, MainActivity.this);
        }
    }

    private SyncStatusObserver mSyncStatusObserver = new SyncStatusObserver() {
        @Override
        public void onStatusChanged(int which) {
            Log.i(TAG, "onStatusChanged: " + which);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Account account = GenericAccountService.getAccount();
                    if (account == null) {
                        setRefreshState(false);
                        return;
                    }

                    boolean syncActive = ContentResolver
                            .isSyncActive(account, MyProvider.AUTHORITY);
                    boolean syncPending = ContentResolver
                            .isSyncPending(account, MyProvider.AUTHORITY);

                    setRefreshState(syncActive || syncPending);
                }
            });
        }
    };
}