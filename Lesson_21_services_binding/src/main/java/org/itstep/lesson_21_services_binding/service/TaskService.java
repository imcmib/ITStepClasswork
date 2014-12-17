package org.itstep.lesson_21_services_binding.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * TaskService.java
 *
 * Created by aivanchenko on 17.12.2014, 13:02
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TaskService extends Service {

	private static final String TAG = TaskService.class.getSimpleName();

	public static final String EXTRA_KEY_TIME = "EXTRA_KEY_TIME";
	public static final String EXTRA_KEY_INTENT = "EXTRA_KEY_INTENT";
	public static final String EXTRA_KEY_RESULT = "EXTRA_KEY_RESULT";

	public static final int STATUS_START = 1;
	public static final int STATUS_FINISH = 2;

	private ExecutorService mExecutorService;

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");

		mExecutorService = Executors.newFixedThreadPool(3);
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		Log.v(TAG, "onStartCommand");

		final int time = intent.getIntExtra(EXTRA_KEY_TIME, 1);
		final PendingIntent pendingIntent = intent.getParcelableExtra(EXTRA_KEY_INTENT);

		final MyTask myTask = new MyTask(time, startId, pendingIntent);
		mExecutorService.execute(myTask);

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

	private class MyTask implements Runnable {

		final int mTime;
		final int mStartId;
		final PendingIntent mPendingIntent;

		public MyTask(int time, int startId, PendingIntent pendingIntent) {
			mTime = time;
			mStartId = startId;
			mPendingIntent = pendingIntent;

			Log.v(TAG, "MyTask#" + startId + " create");
		}

		@Override
		public void run() {
			Log.v(TAG, "MyTask#" + mStartId + " start, time = " + mTime);
			try {
				mPendingIntent.send(STATUS_START);

				TimeUnit.SECONDS.sleep(mTime);

				Intent intent = new Intent().putExtra(EXTRA_KEY_RESULT, mTime);
				mPendingIntent.send(TaskService.this, STATUS_FINISH, intent);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (PendingIntent.CanceledException e) {
				e.printStackTrace();
			}

			Log.v(TAG, "MyTask#" + mStartId + " end, stopSelfResult(" + mStartId + ") = " + stopSelfResult(mStartId));
		}
	}
}