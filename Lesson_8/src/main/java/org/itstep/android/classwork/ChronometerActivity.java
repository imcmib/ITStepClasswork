package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

/*
 * ImageViewActivity.java
 *
 * Created by aivanchenko on 29.10.2014, 12:49
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ChronometerActivity extends Activity implements View.OnClickListener {

	private static final String TAG = ChronometerActivity.class.getSimpleName();

	private Chronometer mChronometer;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ChronometerActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chronometer);

		final TextView textView = (TextView) findViewById(R.id.textView);

		mChronometer = (Chronometer) findViewById(R.id.chronometer);
		mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				final long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
				textView.setText(String.format("Прошло %d секунд.", elapsedMillis / 1000));
			}
		});

		findViewById(R.id.formatCustomButton).setOnClickListener(this);

		findViewById(R.id.startButton).setOnClickListener(this);
		findViewById(R.id.stopButton).setOnClickListener(this);
		findViewById(R.id.resetButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.formatCustomButton:
				mChronometer.setFormat("Time: %s");
				break;
			case R.id.startButton:
				mChronometer.start();
				break;
			case R.id.stopButton:
				mChronometer.stop();
				break;
			case R.id.resetButton:
				mChronometer.setBase(SystemClock.elapsedRealtime() + 60 * 1000);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}