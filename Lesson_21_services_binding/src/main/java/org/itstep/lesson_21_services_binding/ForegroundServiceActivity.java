package org.itstep.lesson_21_services_binding;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import org.itstep.lesson_21_services_binding.service.ForegroundService;

/*
 * PendingIntentResultActivity.java
 *
 * Created by aivanchenko on 17.12.2014, 12:48
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ForegroundServiceActivity extends Activity implements View.OnClickListener {

	private static final String TAG = ForegroundServiceActivity.class.getSimpleName();

	public static final String ACTION_TEST = BuildConfig.APPLICATION_ID + ".ACTION_TEST";

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ForegroundServiceActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foreground_service);

		findViewById(R.id.startServiceButton).setOnClickListener(this);
		findViewById(R.id.stopServiceButton).setOnClickListener(this);
		findViewById(R.id.makeForegroundButton).setOnClickListener(this);
		findViewById(R.id.unmakeForegroundButton).setOnClickListener(this);
		findViewById(R.id.makeNotificationButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		final Intent intent = new Intent(this, ForegroundService.class);
		switch (view.getId()) {
			case R.id.startServiceButton:
				startService(intent);
				break;
			case R.id.stopServiceButton:
				stopService(intent);
				break;
			case R.id.makeForegroundButton:
				intent.putExtra(ForegroundService.EXTRA_KEY_COMMAND,
						ForegroundService.COMMAND_MAKE_FOREGROUND);
				startService(intent);
				break;
			case R.id.unmakeForegroundButton:
				intent.putExtra(ForegroundService.EXTRA_KEY_COMMAND,
						ForegroundService.COMMAND_UNMAKE_FOREGROUND);
				startService(intent);
				break;
			case R.id.makeNotificationButton:
				makeNotification();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void makeNotification() {
		final Intent intent = new Intent(ACTION_TEST);
		final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

		final Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Test")
				.setContentText("Testing!")
				.setContentIntent(pendingIntent)
				.setOngoing(true)
				.setAutoCancel(true)
				.build();

		final NotificationManager notificationManager =
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, notification);
	}
}