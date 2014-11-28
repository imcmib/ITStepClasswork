package org.itstep.android.lesson_17.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import org.itstep.android.lesson_17.R;
import org.itstep.android.lesson_17.receiver.PendingIntentReceiver;

/*
 * PendingIntentActivity.java
 *
 * Created by aivanchenko on 28.11.2014, 14:43
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class PendingIntentActivity extends Activity implements View.OnClickListener {

	private static final String TAG = PendingIntentReceiver.class.getSimpleName();

	private static final int REQUEST_CODE_1 = 100;
	private static final int REQUEST_CODE_2 = 101;

	private static final int NOTIFICATION_ID_1 = 1;
	private static final int NOTIFICATION_ID_2 = 2;

	private NotificationManager mNotificationManager;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, PendingIntentActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pending_intent);

		findViewById(R.id.compareIntentsButton).setOnClickListener(this);
		findViewById(R.id.create1NotificationButton).setOnClickListener(this);
		findViewById(R.id.create2NotificationButton).setOnClickListener(this);
		findViewById(R.id.cancel1NotificationButton).setOnClickListener(this);
		findViewById(R.id.createActivityNotificationButton).setOnClickListener(this);

		mNotificationManager = (NotificationManager)
				getSystemService(Context.NOTIFICATION_SERVICE);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.compareIntentsButton:
				onCompareIntentClick();
				break;
			case R.id.create1NotificationButton:
				onCreate1NotificationClick();
				break;
			case R.id.create2NotificationButton:
				onCreate2NotificationClick();
				break;
			case R.id.cancel1NotificationButton:
				onCancel1NotificationClick();
				break;
			case R.id.createActivityNotificationButton:
				onCreateActivityNotificationClick();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void onCompareIntentClick() {
		final Intent intent1 = createIntent(
				PendingIntentReceiver.ACTION_MY_1, "extra 1");
		final PendingIntent pendingIntent1
				= PendingIntent.getBroadcast(this, 0, intent1, 0);

		final Intent intent2 = createIntent(
				PendingIntentReceiver.ACTION_MY_1, "extra 2");
		final PendingIntent pendingIntent2
				= PendingIntent.getBroadcast(this, 0, intent2, 0);

		compareIntents(intent1, intent2);
		comparePendingIntents(pendingIntent1, pendingIntent2);
	}

	private void onCreate1NotificationClick() {
		final Intent intent = createIntent(
				PendingIntentReceiver.ACTION_MY_1, "extra 1");
		final PendingIntent pendingIntent = PendingIntent.getBroadcast(
				this, 0, intent, 0);

		final Intent intent2 = createIntent(
				PendingIntentReceiver.ACTION_MY_1, "extra 2");
		final PendingIntent pendingIntent2 = PendingIntent.getBroadcast(
				this, 0, intent2, 0);

//		final PendingIntent pendingIntent = PendingIntent.getBroadcast(
//				this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//		final PendingIntent pendingIntent = PendingIntent.getBroadcast(
// 				this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//		final PendingIntent pendingIntent = PendingIntent.getBroadcast(
// 				this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

		sendNotification(NOTIFICATION_ID_1, pendingIntent);
		sendNotification(NOTIFICATION_ID_2, pendingIntent2);
	}

	private void onCreate2NotificationClick() {
		final Intent intent = createIntent(PendingIntentReceiver.ACTION_MY_2, "extra 2");
//		final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		final PendingIntent pendingIntent = PendingIntent.getBroadcast(
				this, 0, intent, PendingIntent.FLAG_NO_CREATE);

		Log.v(TAG, pendingIntent == null ? "pendingIntent is null"
				: "pendingIntent created");

		sendNotification(NOTIFICATION_ID_2, pendingIntent);
	}

	private void onCancel1NotificationClick() {
		mNotificationManager.cancel(NOTIFICATION_ID_1);
	}

	private void onCreateActivityNotificationClick() {
		final Intent intent = new Intent(this, TestActivity.class);
		final PendingIntent pendingIntent = PendingIntent.getActivity(
				this, 0, intent, 0);

		sendNotification(0, pendingIntent);
	}

	private Intent createIntent(final String action, final String extra) {
		final Intent intent = new Intent(this, PendingIntentReceiver.class);
		intent.setAction(action);
		intent.putExtra(PendingIntentReceiver.EXTRA_KEY_MY_EXTRA, extra);
		return intent;
	}

	private void compareIntents(final Intent intent1, final Intent intent2) {
		Log.d(TAG, "intent1 = intent2: " + intent1.filterEquals(intent2));
	}

	private void comparePendingIntents(final PendingIntent pendingIntent1, final PendingIntent pendingIntent2) {
		Log.d(TAG, "pendingIntent1 = pendingIntent2: " + pendingIntent1.equals(pendingIntent2));
	}

	void sendNotification(final int id, final PendingIntent pendingIntent) {
		final Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Notification #" + id)
				.setContentText("Some message")
				.setContentIntent(pendingIntent)
				.build();

		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		mNotificationManager.notify(id, notification);
	}
}