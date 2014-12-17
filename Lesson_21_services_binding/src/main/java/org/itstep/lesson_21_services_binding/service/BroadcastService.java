package org.itstep.lesson_21_services_binding.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.itstep.lesson_21_services_binding.BuildConfig;

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
public class BroadcastService extends Service {

	private static final String TAG = BroadcastService.class.getSimpleName();

	public static final String ACTION_TASK_STATUS = BuildConfig.APPLICATION_ID + ".ACTION_TASK_STATUS";

	public static final String EXTRA_KEY_TASK_ID = "EXTRA_KEY_TASK_ID";
	public static final String EXTRA_KEY_TIME = "EXTRA_KEY_TIME";
	public static final String EXTRA_KEY_STATUS = "EXTRA_KEY_STATUS";
	public static final String EXTRA_KEY_RESULT = "EXTRA_KEY_RESULT";

	public static final int STATUS_START = 1;
	public static final int STATUS_FINISH = 2;

	private ExecutorService mExecutorService;

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");

		mExecutorService = Executors.newFixedThreadPool(1);
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		Log.v(TAG, "onStartCommand");

		final int taskId = intent.getIntExtra(EXTRA_KEY_TASK_ID, 0);
		final int time = intent.getIntExtra(EXTRA_KEY_TIME, 1);

		final MyTask myTask = new MyTask(taskId, time, startId);
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

		final int mTaskId;
		final int mTime;
		final int mStartId;

		public MyTask(int taskId, int time, int startId) {
			mTaskId = taskId;
			mTime = time;
			mStartId = startId;

			Log.v(TAG, "MyTask#" + startId + " create");
		}

		public void run() {
			Log.v(TAG, "MyTask#" + mStartId + " start, time = " + mTime);

			final Intent intent = new Intent(ACTION_TASK_STATUS);
			intent.putExtra(EXTRA_KEY_TASK_ID, mTaskId);

			try {
				intent.putExtra(EXTRA_KEY_STATUS, STATUS_START);
				sendBroadcast(intent);

				TimeUnit.SECONDS.sleep(mTime);

				intent.putExtra(EXTRA_KEY_STATUS, STATUS_FINISH);
				intent.putExtra(EXTRA_KEY_RESULT, mTime);
				sendBroadcast(intent);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Log.v(TAG, "MyTask#" + mStartId + " end, stopSelfResult(" + mStartId + ") = " + stopSelfResult(mStartId));
		}
	}
}