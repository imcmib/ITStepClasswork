package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/*
 * MainActivity.java
 *
 * Created by MiB on 27.10.2014, 16:36
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SeekBarActivity extends Activity implements View.OnClickListener {

	private static final String TAG = SeekBarActivity.class.getSimpleName();

	private SeekBar mSeekBar;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SeekBarActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_seek_bar);

		final TextView progressTextView = (TextView) findViewById(R.id.progressTextView);

		final TextView stateTextView = (TextView) findViewById(R.id.stateTextView);

		mSeekBar = (SeekBar) findViewById(R.id.seekBar);
		mSeekBar.setMax(300);
		mSeekBar.setProgress(100);
		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(final SeekBar seekBar, final int i, final boolean b) {
				progressTextView.setText(String.format("Progress: %d, %s", i, String.valueOf(b)));
			}

			@Override
			public void onStartTrackingTouch(final SeekBar seekBar) {
				stateTextView.setText("onStartTrackingTouch");
			}

			@Override
			public void onStopTrackingTouch(final SeekBar seekBar) {
				stateTextView.setText("onStopTrackingTouch");
			}
		});

		findViewById(R.id.button).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.button:
				mSeekBar.setProgress(50);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}