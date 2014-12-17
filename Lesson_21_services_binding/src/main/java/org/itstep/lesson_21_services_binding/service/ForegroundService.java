package org.itstep.lesson_21_services_binding.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.itstep.lesson_21_services_binding.R;

/*
 * BoundService.java
 *
 * Created by aivanchenko on 17.12.2014, 11:47
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ForegroundService extends Service {

	private static final String TAG = ForegroundService.class.getSimpleName();

	public static final String EXTRA_KEY_COMMAND = "EXTRA_KEY_COMMAND";

	public static final int COMMAND_MAKE_FOREGROUND = 1;
	public static final int COMMAND_UNMAKE_FOREGROUND = 2;

	public static final int ID = 1337;

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		Log.v(TAG, "onStartCommand");

		if (intent != null && intent.getExtras() != null
				&& intent.getExtras().containsKey(EXTRA_KEY_COMMAND)) {
			final int command = intent.getIntExtra(EXTRA_KEY_COMMAND, 0);
			switch (command) {
				case COMMAND_MAKE_FOREGROUND:
					makeForeground();
					break;
				case COMMAND_UNMAKE_FOREGROUND:
					stopForeground(false);
					break;
				default:
					Log.w(TAG, "Command not found!");
			}
		}

		return super.onStartCommand(intent, flags, startId);
	}

	private void makeForeground() {
		final Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Foreground service")
				.setContentText("Testing")
				.build();

		startForeground(ID, notification);
	}

	@Override
	public IBinder onBind(final Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.v(TAG, "onDestroy");
	}
}
