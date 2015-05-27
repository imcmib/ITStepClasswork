package org.itstep.android.lesson_43;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_audio_playback).setOnClickListener(this);
        findViewById(R.id.button_audio_capture).setOnClickListener(this);
        findViewById(R.id.button_video_playback).setOnClickListener(this);
        findViewById(R.id.button_video_capture).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_audio_playback:
                AudioPlaybackActivity.startActivity(this);
                break;
            case R.id.button_audio_capture:
                AudioCaptureActivity.startActivity(this);
                break;
            case R.id.button_video_playback:
                VideoPlaybackActivity.startActivity(this);
                break;
            case R.id.button_video_capture:
                VideoCaptureActivity.startActivity(this);
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }
}
