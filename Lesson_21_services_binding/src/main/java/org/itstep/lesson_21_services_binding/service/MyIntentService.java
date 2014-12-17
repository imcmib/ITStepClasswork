package org.itstep.lesson_21_services_binding.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/*
 * MyIntentService.java
 *
 * Created by aivanchenko on 17.12.2014, 14:27
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyIntentService extends IntentService {

	private static final String TAG = MyIntentService.class.getSimpleName();

	public static final String EXTRA_KEY_TIME = "EXTRA_KEY_TIME";
	public static final String EXTRA_KEY_LABEL = "EXTRA_KEY_LABEL";

	public MyIntentService() {
		super(MyIntentService.class.getSimpleName());
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "onCreate");
	}

	@Override
	protected void onHandleIntent(final Intent intent) {
		final int time = intent.getIntExtra(EXTRA_KEY_TIME, 0);
		final String label = intent.getStringExtra(EXTRA_KEY_LABEL);
		Log.v(TAG, "onHandleIntent: start " + label);
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Log.v(TAG, "onHandleIntent: end " + label);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.v(TAG, "onDestroy");
	}
}