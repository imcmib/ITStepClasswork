package org.itstep.android.lesson19.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import org.itstep.android.lesson19.BuildConfig;
import org.itstep.android.lesson19.MessageActivity;
import org.itstep.android.lesson19.R;

/*
 * SimpleReceiver.java
 *
 * Created by aivanchenko on 10.12.2014, 16:31
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SimpleReceiver extends BroadcastReceiver {

	public static final String ACTION_NEW_MESSAGE = BuildConfig.APPLICATION_ID + ".ACTION_NEW_MESSAGE";

	public static final String EXTRA_KEY_MESSAGE = "EXTRA_KEY_MESSAGE";

	@Override
	public void onReceive(final Context context, final Intent intent) {
		if (intent.getAction().equals(ACTION_NEW_MESSAGE)) {
			showNotification(context, intent);
		}
	}

	private void showNotification(final Context context, final Intent intent) {
		final Intent activityIntent = new Intent(context, MessageActivity.class);
		activityIntent.putExtras(intent);

		final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, 0);

		final Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		final Notification notification = new NotificationCompat.Builder(context)
				.setAutoCancel(true)
				.setSound(sound)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle(context.getString(R.string.title_new_message))
				.setContentText(intent.getStringExtra(EXTRA_KEY_MESSAGE))
				.setContentIntent(pendingIntent)
				.build();

		final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}
}