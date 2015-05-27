package org.itstep.android.lesson_42;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.itstep.android.lesson_42.network.AuthBody;
import org.itstep.android.lesson_42.network.GitHub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

/*
 * GithubAuthActivity.java
 *
 * Created by mib on 18.02.15, 19:02
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class GithubAuthActivity extends ActionBarActivity {

    private static final String TAG = GithubAuthActivity.class.getSimpleName();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_auth);

        final WebViewClient client = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view,
                                                    final String url) {
                Log.i(TAG, "Url: " + url);
                if (url.startsWith(Constants.AUTH_REDIRECT_URL)) {
                    final String code = Uri.parse(url).getQueryParameter("code");
                    Log.i(TAG, "Code: " + code);

                    sendTokenRequest(code);

                    return true;
                }

                return false;
            }
        };

        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(client);
        webView.loadUrl(Constants.getAuthUrl());
    }

    private void sendTokenRequest(final String code) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.AUTH_BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        final AuthBody body = new AuthBody(
                Constants.CLIENT_ID,
                Constants.CLIENT_SECRET,
                code,
                Constants.AUTH_REDIRECT_URL);

        GitHub service = restAdapter.create(GitHub.class);
        service.auth(body, new Callback<Response>() {
            @Override
            public void success(final Response response1, final Response response2) {
                String authResult = getStringResponse(response1);

                final String accessToken = Uri.parse("http://localhost?" + authResult)
                                              .getQueryParameter("access_token");
                Log.i(TAG, "Access token: " + accessToken);

                saveAccount(accessToken);
            }

            @Override
            public void failure(final RetrofitError error) {
                Log.e(TAG, "Error: " + error);
            }
        });
    }

    private void saveAccount(final String accessToken) {
        Account account = new Account(Constants.ACCOUNT, BuildConfig.APPLICATION_ID);

        AccountManager accountManager = (AccountManager) getSystemService(Context.ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(account, null, null)) {
            Log.i(TAG, "Account created");
        } else {
            Log.i(TAG, "Account NOT created");
        }
        accountManager.setAuthToken(account, Constants.TOKEN_TYPE, accessToken);
        accountManager.getAuthToken(account, Constants.TOKEN_TYPE, null, this,
                new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(final AccountManagerFuture<Bundle> accountManagerFuture) {
                        try {
                            final Bundle result = accountManagerFuture.getResult();
                            final String authtoken = result.getString("authtoken");
                            Log.i(TAG, "token: " + authtoken);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Handler());
        accountManager.setUserData(account, "some_key", "some_value");

        final String someKey = accountManager.getUserData(account, "some_key");
        Log.i(TAG, "value: " + someKey);
    }

    private String getStringResponse(final Response response1) {
        TypedInput body = response1.getBody();
        StringBuilder out = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(body.in()));
            String newLine = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append(newLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}