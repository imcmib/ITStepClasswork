package org.itstep.lesson_21_services_binding.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/*
 * BoundService.java
 *
 * Created by aivanchenko on 17.12.2014, 11:47
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ConnectionService extends Service {

	private static final String TAG = ConnectionService.class.getSimpleName();

	private final LocalBinder mMyBinder = new LocalBinder();

	private Timer mTimer;
	private TimerTask mTimerTask;
	private long mInterval = 1000;

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");

		mTimer = new Timer();
		schedule();
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		Log.v(TAG, "onStartCommand");

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(final Intent intent) {
		Log.v(TAG, "onBind");

		return mMyBinder;
	}

	@Override
	public void onRebind(final Intent intent) {
		super.onRebind(intent);

		Log.v(TAG, "onRebind");
	}

	@Override
	public boolean onUnbind(final Intent intent) {
		Log.v(TAG, "onUnbind");

		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.v(TAG, "onDestroy");

		if (mTimerTask != null) {
			mTimerTask.cancel();
		}
	}

	private void schedule() {
		if (mTimerTask != null) {
			mTimerTask.cancel();
		}

		if (mInterval > 0) {
			mTimerTask = new TimerTask() {
				public void run() {
					Log.v(TAG, "run: " + mInterval);
				}
			};
			mTimer.schedule(mTimerTask, 1000, mInterval);
		}
	}

	public long upInterval(long offset) {
		mInterval += offset;

		schedule();

		return mInterval;
	}

	public long downInterval(long offset) {
		mInterval -= offset;
		if (mInterval < 0) {
			mInterval = 0;
		}

		schedule();

		return mInterval;
	}

	public long getInterval() {
		return mInterval;
	}

	public class LocalBinder extends Binder {

		public ConnectionService getService() {
			return ConnectionService.this;
		}
	}
}