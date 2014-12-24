package org.itstep.lesson_21_services_binding.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/*
 * BoundService.java
 *
 * Created by aivanchenko on 17.12.2014, 11:47
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class BoundService extends Service {

	private static final String TAG = BoundService.class.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		Log.v(TAG, "onStartCommand");

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(final Intent intent) {
		Log.v(TAG, "onBind");

		return new Binder();
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
	}
}
