package org.itstep.android.lesson_18;

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
import android.widget.RemoteViews;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	private NotificationManager mNotificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.simpleNotificationButton).setOnClickListener(this);
		findViewById(R.id.expandedNotificationButton).setOnClickListener(this);
		findViewById(R.id.updateNotificationButton).setOnClickListener(this);
		findViewById(R.id.progressNotificationButton).setOnClickListener(this);
		findViewById(R.id.progressInNotificationButton).setOnClickListener(this);
		findViewById(R.id.customNotificationButton).setOnClickListener(this);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.simpleNotificationButton:
				showSimpleNotification();
				break;
			case R.id.expandedNotificationButton:
				showExpandableNotification();
				break;
			case R.id.updateNotificationButton:
				showUpdatableNotification();
				break;
			case R.id.progressNotificationButton:
				showProgressNotification();
				break;
			case R.id.progressInNotificationButton:
				showEndlessProgressNotification();
				break;
			case R.id.customNotificationButton:
				showCustomNotification();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void showCustomNotification() {
		final RemoteViews views = new RemoteViews(
				BuildConfig.APPLICATION_ID, R.layout.notification);
		final Intent intent = new Intent(this, SecondActivity.class);
		final PendingIntent pendingIntent = PendingIntent.getActivity(
				this, 0, intent, 0);
		views.setOnClickPendingIntent(R.id.button, pendingIntent);

		final Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContent(views)
				.build();

		mNotificationManager.notify(0, notification);
	}

	private void showEndlessProgressNotification() {
		final Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Title test")
				.setContentText("Some message")
				.setProgress(100, 0, true)
				.build();

		mNotificationManager.notify(0, notification);
	}

	private void showSimpleNotification() {
		final Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Title test")
				.setContentText("Some message")
//				.setWhen(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5))
				.build();

		final int i = new Random().nextInt();
		mNotificationManager.notify(i, notification);
	}

	private void showExpandableNotification() {
		final NotificationCompat.InboxStyle inboxStyle
				= new NotificationCompat.InboxStyle();
		inboxStyle.setBigContentTitle("Event tracker details:");
		for (int i = 0; i < 6; i++) {
			inboxStyle.addLine(String.format("Event %d", i));
		}
		inboxStyle.setSummaryText("Summary bottom text");

		final Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Title test")
				.setContentText("Some message")
				.setStyle(inboxStyle)
				.build();

		mNotificationManager.notify(0, notification);
	}

	private void showUpdatableNotification() {
		final NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
		inboxStyle.setBigContentTitle("Messages:");
		final int messagesCount = new Random().nextInt(10) + 1;
		for (int i = 0; i < messagesCount; i++) {
			inboxStyle.addLine(String.format("Message %d", i));
		}
		inboxStyle.setSummaryText(String.format("Total count: %d", messagesCount));

		final Notification notification = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Title test")
				.setContentText("Some message")
				.setStyle(inboxStyle)
				.build();

		mNotificationManager.notify(0, notification);
	}

	private void showProgressNotification() {
		final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Picture Download")
				.setContentText("Download in progress");

		new Thread(
				new Runnable() {
					@Override
					public void run() {
						int incr;
						for (incr = 0; incr <= 100; incr+=5) {
							builder.setProgress(100, incr, false);
							mNotificationManager.notify(0, builder.build());
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								Log.d(TAG, "sleep failure");
							}
						}
						builder.setContentText("Download complete")
								.setProgress(0, 0, false);
						mNotificationManager.notify(0, builder.build());
					}
				}
		).start();
	}
}
