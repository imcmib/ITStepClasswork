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

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		Log.v(TAG, "onStartCommand");

		final Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Foreground service")
				.setContentText("Testing")
				.build();

		startForeground(1337, notification);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(final Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		stopForeground(true);

		Log.v(TAG, "onDestroy");
	}
}
