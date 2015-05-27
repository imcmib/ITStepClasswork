package org.itstep.android.lesson_43;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

/*
 * VideoPlaybackActivity.java
 *
 * Created by aivanchenko on 27.05.2015, 17:18
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class VideoPlaybackActivity extends ActionBarActivity {

    private static final String TAG = VideoPlaybackActivity.class.getSimpleName();

    private VideoView myVideoView;

    private MediaController mediaControls;
    private ProgressDialog progressDialog;
    private int position = 0;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, VideoPlaybackActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);

        if (mediaControls == null) {
            mediaControls = new MediaController(this);
        }

        myVideoView = (VideoView) findViewById(R.id.video_view);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Video View Example");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kitkat);

            myVideoView.setMediaController(mediaControls);
            myVideoView.setVideoURI(
                    uri);

        } catch (Exception e) {
            Log.e(TAG, "Error", e);
        }

        myVideoView.requestFocus();
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();

                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    myVideoView.pause();
                }
            }
        });
    }
}