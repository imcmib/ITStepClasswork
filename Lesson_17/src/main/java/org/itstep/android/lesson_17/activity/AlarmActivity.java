package org.itstep.android.lesson_17.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

import org.itstep.android.lesson_17.R;

import java.util.concurrent.TimeUnit;


/*
 * AlarmActivity.java
 *
 * Created by mib on 28.11.14, 20:04
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class AlarmActivity extends Activity implements View.OnClickListener {

	private static final String TAG = AlarmActivity.class.getSimpleName();

	private AlarmManager mAlarmManager;
	private PendingIntent mPendingIntent;
	private Chronometer mChronometer;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, AlarmActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);

		mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		mChronometer = (Chronometer) findViewById(R.id.chronometer);


		findViewById(R.id.createAlarmButton).setOnClickListener(this);
		findViewById(R.id.cancelAlarmButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.createAlarmButton:
				createAlarm();
				break;
			case R.id.cancelAlarmButton:
				cancelAlarm();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void cancelAlarm() {
		mAlarmManager.cancel(mPendingIntent);
	}

	private void createAlarm() {
		final long realTime = SystemClock.elapsedRealtime()
				+ TimeUnit.SECONDS.toMillis(5);

		final Intent intent = new Intent(this, TestActivity.class);

		mPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

//		mAlarmManager.set(AlarmManager.ELAPSED_REALTIME, realTime, pendingIntent);

		final long rtcTime = System.currentTimeMillis()
				+ TimeUnit.SECONDS.toMillis(10);

		final long intervalTime = TimeUnit.SECONDS.toMillis(10);

//		mAlarmManager.setRepeating(
//				AlarmManager.RTC, rtcTime, intervalTime, mPendingIntent);

		mAlarmManager.setInexactRepeating(AlarmManager.RTC, rtcTime, intervalTime, mPendingIntent);

//		final AlarmManager.AlarmClockInfo alarmClockInfo
//				= new AlarmManager.AlarmClockInfo();
//		mAlarmManager.setAlarmClock(alarmClockInfo, mPendingIntent);
	}
}