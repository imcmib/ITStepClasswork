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
public class StickyService extends Service {

	private static final String TAG = StickyService.class.getSimpleName();

	public static final String EXTRA_KEY_DATA = "EXTRA_KEY_DATA";

	private Thread mThread;

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		Log.v(TAG, "onStartCommand");

		if (intent != null && intent.hasExtra(EXTRA_KEY_DATA)) {
			final String text = intent.getStringExtra(EXTRA_KEY_DATA);
			Log.v(TAG, "Data: " + text);
		}

		printFlags(flags);

		doSomeWorkInNewThread();

		return Service.START_REDELIVER_INTENT;
	}

	@Override
	public IBinder onBind(final Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.v(TAG, "onDestroy");

		if (mThread != null) {
			mThread.interrupt();
		}
	}

	private void printFlags(final int flags) {
		if ((flags & START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY) {
			Log.d(TAG, "START_FLAG_REDELIVERY");
		}

		if ((flags & START_FLAG_RETRY) == START_FLAG_RETRY) {
			Log.d(TAG, "START_FLAG_RETRY");
		}
	}

	private void doSomeWorkInNewThread() {
		mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int counter = 0;
					while (true) {
						Log.v(TAG, "Count: " + counter++);
						TimeUnit.SECONDS.sleep(1);
					}
				} catch (Exception e) {
					Log.e(TAG, "Error", e);
				}
			}
		});
		mThread.start();
	}
}
