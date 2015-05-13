package org.itstep.android.lesson_41;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
 * ProvidersActivity.java
 *
 * Created by mib on 13.05.15, 20:32
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ProvidersActivity extends AppCompatActivity {

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, ProvidersActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_providers);

        final TextView textView = (TextView) findViewById(R.id.text_view);

        final List<PackageInfo> packages = getPackageManager()
                .getInstalledPackages(PackageManager.GET_PROVIDERS);
        for (PackageInfo packageInfo : packages) {
            textView.append("\n" + packageInfo.packageName);
            if (packageInfo.providers == null) {
                continue;
            }

            final ProviderInfo[] providers = packageInfo.providers;
            for (ProviderInfo providerInfo : providers) {
                textView.append("\n\t\t--->" + providerInfo.authority);
            }
        }


    }
}
