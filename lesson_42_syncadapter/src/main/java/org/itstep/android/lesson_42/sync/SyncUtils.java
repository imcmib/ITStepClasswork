package org.itstep.android.lesson_42.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.itstep.android.lesson_42.accounts.GenericAccountService;
import org.itstep.android.lesson_42.provider.MyProvider;

public class SyncUtils {

    private static final long SYNC_FREQUENCY = 15;  // 15 sec
    private static final String CONTENT_AUTHORITY = MyProvider.AUTHORITY;
    private static final String PREF_SETUP_COMPLETE = "setup_complete";

    public static void createSyncAccount(Context context) {
        boolean newAccount = false;
        boolean setupComplete = PreferenceManager.getDefaultSharedPreferences(context)
                                                 .getBoolean(PREF_SETUP_COMPLETE, false);

        Account account = GenericAccountService.getAccount();

        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        if (accountManager.addAccountExplicitly(account, null, null)) {
            ContentResolver.setIsSyncable(account, CONTENT_AUTHORITY, 1);
            ContentResolver.setSyncAutomatically(account, CONTENT_AUTHORITY, true);
            ContentResolver.addPeriodicSync(
                    account,
                    CONTENT_AUTHORITY,
                    new Bundle(),
                    SYNC_FREQUENCY);

            newAccount = true;
        }

        if (newAccount || !setupComplete) {
            refresh();

            PreferenceManager.getDefaultSharedPreferences(context)
                             .edit()
                             .putBoolean(PREF_SETUP_COMPLETE, true)
                             .commit();
        }
    }

    public static void refresh() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        ContentResolver.requestSync(
                GenericAccountService.getAccount(),
                CONTENT_AUTHORITY,
                bundle);
    }
}
