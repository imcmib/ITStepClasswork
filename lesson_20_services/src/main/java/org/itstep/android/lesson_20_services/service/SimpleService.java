package org.itstep.android.lesson_20_services.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/*
 * MyService.java
 *
 * Created by aivanchenko on 12.12.2014, 15:11
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SimpleService extends Service {

	private static final String TAG = SimpleService.class.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		Log.v(TAG, "onStartCommand");

		doSomeWorkInMainThread();
//		doSomeWorkInNewThread();

		return super.onStartCommand(intent, flags, startId);
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

	private void doSomeWorkInMainThread() {
		try {
			int counter = 0;
			while (counter < 15) {
				Log.v(TAG, "Count: " + counter++);
				TimeUnit.SECONDS.sleep(1);
			}
//			stopSelf();
		} catch (Exception e) {
			Log.e(TAG, "Error", e);
		}
	}

	private void doSomeWorkInNewThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				doSomeWorkInMainThread();
			}
		}).start();
	}
}
