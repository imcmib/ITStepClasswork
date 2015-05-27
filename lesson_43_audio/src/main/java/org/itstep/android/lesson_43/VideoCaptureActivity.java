package org.itstep.android.lesson_43;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

/*
 * VideoPlaybackActivity.java
 *
 * Created by aivanchenko on 27.05.2015, 17:18
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class VideoCaptureActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = VideoCaptureActivity.class.getSimpleName();

    private static final int REQUEST_VIDEO_CAPTURE = 1;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, VideoCaptureActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_capture);

        findViewById(R.id.button_record).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_record:
                dispatchTakeVideoIntent();
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            // TODO [aivanchenko | 27.05.2015 17:28]
        }
    }
}