package org.itstep.android.lesson_17.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.itstep.android.lesson_17.BuildConfig;

/*
 * PendingIntentReceiver.java
 *
 * Created by aivanchenko on 28.11.2014, 14:45
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class PendingIntentReceiver extends BroadcastReceiver {

	public static final String TAG = PendingIntentReceiver.class.getSimpleName();

	public static final String ACTION_MY_1 = BuildConfig.APPLICATION_ID + ".ACTION_MY_1";
	public static final String ACTION_MY_2 = BuildConfig.APPLICATION_ID + ".ACTION_MY_2";

	public static final String EXTRA_KEY_MY_EXTRA = BuildConfig.APPLICATION_ID + ".EXTRA_KEY_MY_EXTRA";

	@Override
	public void onReceive(final Context context, final Intent intent) {
		Log.v(TAG, "onReceive");
		Log.v(TAG, "Action: " + intent.getAction());
		Log.v(TAG, "Extra: " + intent.getStringExtra(EXTRA_KEY_MY_EXTRA));
	}
}