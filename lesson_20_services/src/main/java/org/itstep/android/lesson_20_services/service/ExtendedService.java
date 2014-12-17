package org.itstep.android.lesson_20_services.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
public class ExtendedService extends Service {

	private static final String TAG = ExtendedService.class.getSimpleName();

	public static final String EXTRA_KEY_TIME = "EXTRA_KEY_TIME";

	private static final int THREADS_COUNT = 3;

	private ExecutorService mExecutorService;

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");

		mExecutorService = Executors.newFixedThreadPool(THREADS_COUNT);
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		Log.v(TAG, "onStartCommand");

		final int time = intent.getIntExtra(EXTRA_KEY_TIME, 0);
		if (time > 0) {
			final MyRunnable runnable = new MyRunnable(startId, time);
			mExecutorService.execute(runnable);
		}

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

	private class MyRunnable implements Runnable {

		private int mStartId;
		private int mTime;

		private MyRunnable(final int startId, final int time) {
			mStartId = startId;
			mTime = time;

			Log.v(TAG, "MyRunnable created,\tstartId=" + mStartId + ", time=" + mTime);
		}

		@Override
		public void run() {
			Log.v(TAG, "MyRunnable running,\tstartId=" + mStartId);

			try {
				TimeUnit.SECONDS.sleep(mTime);
			} catch (Exception e) {
				Log.e(TAG, "Error", e);
			}

			Log.v(TAG, "MyRunnable done,\tstartId=" + mStartId);

			stopSelf(mStartId);
		}
	}
}
