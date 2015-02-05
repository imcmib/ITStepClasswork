package org.itstep.android.lesson_29_network;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

/*
 * TextEditorActivity.java
 *
 * Created by aivanchenko on 04.02.2015, 16:11
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class FilesActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = FilesActivity.class.getSimpleName();

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, FilesActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dirs);

        final StringBuilder builder = new StringBuilder();

        final File dir = Environment.getExternalStorageDirectory();
        if (dir.isDirectory() && dir.exists()) {
            for (File file : dir.listFiles()) {
                builder.append(file.isDirectory() ? "DIR:\t" : "FILE:\t")
                       .append(String.format("%s\t[%d]", file.getName(), file.length()))
                       .append("\n");
            }
        }

		final TextView textView = (TextView) findViewById(R.id.textView);
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