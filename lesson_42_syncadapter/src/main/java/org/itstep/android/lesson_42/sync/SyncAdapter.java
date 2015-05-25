package org.itstep.android.lesson_42.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import org.itstep.android.lesson_42.provider.MyProvider;

class SyncAdapter extends AbstractThreadedSyncAdapter {

    public static final String TAG = "SyncAdapter";

    private final ContentResolver mContentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {
        Log.i(TAG, "onPerformSync");

        final Cursor cursor =
                mContentResolver.query(MyProvider.CONTACT_CONTENT_URI, null, null, null, null);
        final int count = cursor.getCount();
        cursor.close();

        for (int i = count; i < count + 3; i++) {
            final String name = String.format("name_%d", i);

            final ContentValues contentValues = new ContentValues();
            contentValues.put(MyProvider.CONTACT_NAME, name);
            contentValues.put(MyProvider.CONTACT_EMAIL, String.format("%s@mail.com", name));

            mContentResolver.insert(MyProvider.CONTACT_CONTENT_URI, contentValues);
        }
    }
}
