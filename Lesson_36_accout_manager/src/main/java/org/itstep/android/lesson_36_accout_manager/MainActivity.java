package org.itstep.android.lesson_36_accout_manager;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private AccountManager mAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAccountManager = AccountManager.get(this);
//        getSystemService(Context.ACCOUNT_SERVICE);
        final Account[] accounts = mAccountManager.getAccounts();
        for (Account account : accounts) {
            Log.i(TAG, account.name + ", " + account.type);
        }

        final EditText login = (EditText) findViewById(R.id.login);
        final EditText password = (EditText) findViewById(R.id.password);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                createAccount(login.getText().toString(), password.getText().toString());
            }
        });
    }

    private void createAccount(final String login, final String password) {
        final Account account = new Account(login, BuildConfig.APPLICATION_ID);
        final boolean isSuccess = mAccountManager
                .addAccountExplicitly(account, password, null);
        Log.i(TAG, "isSuccess: " + isSuccess);
    }
}