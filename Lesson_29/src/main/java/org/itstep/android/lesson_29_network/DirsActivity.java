package org.itstep.android.lesson_29_network;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/*
 * TextEditorActivity.java
 *
 * Created by aivanchenko on 04.02.2015, 16:11
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class DirsActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = DirsActivity.class.getSimpleName();

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, DirsActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dirs);

        final TextView textView = (TextView) findViewById(R.id.textView);

        final StringBuilder builder = new StringBuilder();
        builder.append("DataDirectory:\n")
               .append(Environment.getDataDirectory())
               .append("\n\n");

        builder.append("DownloadCacheDirectory:\n")
               .append(Environment.getDownloadCacheDirectory())
               .append("\n\n");

        builder.append("ExternalStorageState:\n")
               .append(Environment.getExternalStorageState())
               .append("\n\n");

        builder.append("ExternalStorageDirectory:\n")
               .append(Environment.getExternalStorageDirectory())
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_ALARMS):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS))
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_DCIM):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM))
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS))
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_MOVIES):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES))
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_MUSIC):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC))
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_NOTIFICATIONS):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS))
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_PICTURES):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES))
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_PODCASTS):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS))
               .append("\n\n");

        builder.append("ExternalStoragePublicDirectory(DIRECTORY_RINGTONES):\n")
               .append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES))
               .append("\n\n");

        builder.append("RootDirectory:\n")
               .append(Environment.getRootDirectory());

        textView.setText(builder);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }
}