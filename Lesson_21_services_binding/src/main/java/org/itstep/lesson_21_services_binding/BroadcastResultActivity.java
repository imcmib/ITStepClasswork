package org.itstep.lesson_21_services_binding;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.itstep.lesson_21_services_binding.service.BroadcastService;

/*
 * PendingIntentResultActivity.java
 *
 * Created by aivanchenko on 17.12.2014, 12:48
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class BroadcastResultActivity extends Activity {

	private static final String TAG = BroadcastResultActivity.class.getSimpleName();

	private static final int TASK_ID_1 = 1;
	private static final int TASK_ID_2 = 2;
	private static final int TASK_ID_3 = 3;

	private TextView mTask1TextView;
	private TextView mTask2TextView;
	private TextView mTask3TextView;
	private BroadcastReceiver mBroadcastReceiver;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, BroadcastResultActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(org.itstep.lesson_21_services_binding.R.layout.activity_pending_intent_result);

		mTask1TextView = (TextView) findViewById(org.itstep.lesson_21_services_binding.R.id.task1TextView);
		mTask2TextView = (TextView) findViewById(org.itstep.lesson_21_services_binding.R.id.task2TextView);
		mTask3TextView = (TextView) findViewById(org.itstep.lesson_21_services_binding.R.id.task3TextView);

		findViewById(org.itstep.lesson_21_services_binding.R.id.startButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				onStartButtonClick();
			}
		});

		mBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(final Context context, final Intent intent) {
				final int taskId = intent.getIntExtra(BroadcastService.EXTRA_KEY_TASK_ID, 0);
				final int status = intent.getIntExtra(BroadcastService.EXTRA_KEY_STATUS, 0);
				Log.d(TAG, "onReceive: task = " + taskId + ", status = " + status);

				if (status == BroadcastService.STATUS_START) {
					switch (taskId) {
						case TASK_ID_1:
							mTask1TextView.setText("Task started");
							break;
						case TASK_ID_2:
							mTask2TextView.setText("Task started");
							break;
						case TASK_ID_3:
							mTask3TextView.setText("Task started");
							break;
						default:
							Log.w(TAG, "Unknown task id: " + taskId);
							break;
					}
				}

				if (status == BroadcastService.STATUS_FINISH) {
					final int result = intent.getIntExtra(BroadcastService.EXTRA_KEY_RESULT, 0);
					switch (taskId) {
						case TASK_ID_1:
							mTask1TextView.setText("Task finished, result: " + result);
							break;
						case TASK_ID_2:
							mTask2TextView.setText("Task finished, result: " + result);
							break;
						case TASK_ID_3:
							mTask3TextView.setText("Task finished, result: " + result);
							break;
						default:
							Log.w(TAG, "Unknown task id: " + taskId);
							break;
					}
				}
			}
		};

		final IntentFilter intentFilter = new IntentFilter(BroadcastService.ACTION_TASK_STATUS);
		registerReceiver(mBroadcastReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		unregisterReceiver(mBroadcastReceiver);
	}

	private void onStartButtonClick() {
		startService(TASK_ID_1, 10);
		startService(TASK_ID_2, 5);
		startService(TASK_ID_3, 7);
	}

	private void startService(int taskId, int time) {
		final Intent intent = new Intent(this, BroadcastService.class)
				.putExtra(BroadcastService.EXTRA_KEY_TASK_ID, taskId)
				.putExtra(BroadcastService.EXTRA_KEY_TIME, time);

		startService(intent);
	}
}