package org.itstep.android.lesson_43;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

public class AudioPlaybackActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = AudioPlaybackActivity.class.getSimpleName();

    private MediaPlayer mMediaPlayer;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, AudioPlaybackActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_playback);

        findViewById(R.id.button_play).setOnClickListener(this);
        findViewById(R.id.button_stop).setOnClickListener(this);
        findViewById(R.id.button_pause).setOnClickListener(this);

        mMediaPlayer = MediaPlayer.create(this, R.raw.sound1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_play:
                mMediaPlayer.start();
                break;
            case R.id.button_stop:
                mMediaPlayer.stop();
                mMediaPlayer.reset();
                break;
            case R.id.button_pause:
                mMediaPlayer.pause();
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }
}