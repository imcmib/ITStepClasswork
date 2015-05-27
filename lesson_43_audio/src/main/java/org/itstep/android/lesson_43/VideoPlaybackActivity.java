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

public class VideoPlaybackActivity extends ActionBarActivity {

    private static final String TAG = VideoPlaybackActivity.class.getSimpleName();
    public static final String EXTRA_URI = "EXTRA_URI";

    private VideoView myVideoView;

    private MediaController mediaControls;
    private ProgressDialog progressDialog;
    private int position = 0;

    public static void startActivity(Activity context) {
        startActivity(context, null);
    }

    public static void startActivity(Activity context, Uri uri) {
        final Intent intent = new Intent(context, VideoPlaybackActivity.class);
        if (uri != null) {
            intent.putExtra(EXTRA_URI, uri);
        }

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);

        final Uri uri;
        if (getIntent().hasExtra(EXTRA_URI)) {
            uri = getIntent().getParcelableExtra(EXTRA_URI);
        } else {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kitkat);
        }

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
            myVideoView.setMediaController(mediaControls);
            myVideoView.setVideoURI(uri);
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