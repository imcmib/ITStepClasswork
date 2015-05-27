package org.itstep.android.lesson_43;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class AudioPlaybackActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = AudioPlaybackActivity.class.getSimpleName();

    private final Handler mHandler = new Handler();

    private MediaPlayer mMediaPlayer;
    private boolean mPrepared;
    private View mPlayButton;
    private TextView mStateTextView;
    private SeekBar mSeekBar;

    private Runnable mUpdatePositionRunnable = new Runnable() {
        @Override
        public void run() {
            mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());

            mHandler.postDelayed(this, 100);
        }
    };

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, AudioPlaybackActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_playback);

        mPlayButton = findViewById(R.id.button_play);
        mPlayButton.setOnClickListener(this);
        findViewById(R.id.button_stop).setOnClickListener(this);
        findViewById(R.id.button_pause).setOnClickListener(this);

        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int i, final boolean b) {
                mStateTextView.setText("Position: " + i);
            }

            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {

            }
        });

        mStateTextView = (TextView) findViewById(R.id.state_text_view);

        mStateTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                createMediaPlayer();
            }
        }, 3000);

        updatePlayButton();
    }

    private void createMediaPlayer() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.sound1);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(final MediaPlayer mediaPlayer) {
                mStateTextView.setText("Completed");
            }
        });
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mediaPlayer) {
                mPrepared = true;

                mSeekBar.setMax(mediaPlayer.getDuration());

                updatePlayButton();

                mStateTextView.setText("Prepared");
            }
        });
        mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(final MediaPlayer mediaPlayer, final int i, final int i1) {
                Log.i(TAG, "Info: " + i);
                return false;
            }
        });
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(final MediaPlayer mediaPlayer, final int i, final int i1) {
                Log.i(TAG, "Error: " + i);
                return false;
            }
        });
        mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(final MediaPlayer mediaPlayer) {
                Log.i(TAG, "onSeekComplete: " + mediaPlayer.getCurrentPosition());
//                mHandler.removeCallbacks(mUpdatePositionRunnable);
            }
        });
        mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(final MediaPlayer mediaPlayer, final int i) {
                Log.i(TAG, "onBufferingUpdate: " + i);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_play:
                mMediaPlayer.seekTo(mSeekBar.getProgress());
                mMediaPlayer.start();

                mHandler.postDelayed(mUpdatePositionRunnable, 100);
                break;
            case R.id.button_stop:
                mMediaPlayer.stop();

                mPrepared = false;
                updatePlayButton();

                mHandler.removeCallbacks(mUpdatePositionRunnable);

                mMediaPlayer.prepareAsync();
                break;
            case R.id.button_pause:
                mMediaPlayer.pause();

                mHandler.removeCallbacks(mUpdatePositionRunnable);
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources().getResourceName(view.getId()));
                break;
        }
    }

    private void updatePlayButton() {
        mPlayButton.setEnabled(mPrepared);
    }
}