package org.itstep.lesson_21_services_binding;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.itstep.lesson_21_services_binding.service.BroadcastService;
import org.itstep.lesson_21_services_binding.service.TaskService;

/*
 * PendingIntentResultActivity.java
 *
 * Created by aivanchenko on 17.12.2014, 12:48
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class PendingIntentResultActivity extends Activity {

	private static final String TAG = PendingIntentResultActivity.class.getSimpleName();

	private static final int REQUEST_CODE_TASK_1 = 1;
	private static final int REQUEST_CODE_TASK_2 = 2;
	private static final int REQUEST_CODE_TASK_3 = 3;

	private TextView mTask1TextView;
	private TextView mTask2TextView;
	private TextView mTask3TextView;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, PendingIntentResultActivity.class);

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
	}

	private void onStartButtonClick() {
		startService(REQUEST_CODE_TASK_1, 10);
		startService(REQUEST_CODE_TASK_2, 5);
		startService(REQUEST_CODE_TASK_3, 7);
	}

	private void startService(int requestCode, int time) {
		final PendingIntent pendingResult = createPendingResult(requestCode, new Intent(), 0);

		final Intent intent = new Intent(this, TaskService.class)
				.putExtra(TaskService.EXTRA_KEY_TIME, time)
				.putExtra(TaskService.EXTRA_KEY_INTENT, pendingResult);

		startService(intent);
	}

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Log.d(TAG, "requestCode = " + requestCode + ", resultCode = " + resultCode);

		if (resultCode == TaskService.STATUS_START) {
			switch (requestCode) {
				case REQUEST_CODE_TASK_1:
					mTask1TextView.setText("Task started");
					break;
				case REQUEST_CODE_TASK_2:
					mTask2TextView.setText("Task started");
					break;
				case REQUEST_CODE_TASK_3:
					mTask3TextView.setText("Task started");
					break;
				default:
					Log.w(TAG, "Unknown request code: " + requestCode);
					break;
			}
		}

		if (resultCode == TaskService.STATUS_FINISH) {
			final int result = data.getIntExtra(BroadcastService.EXTRA_KEY_RESULT, 0);
			switch (requestCode) {
				case REQUEST_CODE_TASK_1:
					mTask1TextView.setText("Task finished, result: " + result);
					break;
				case REQUEST_CODE_TASK_2:
					mTask2TextView.setText("Task finished, result: " + result);
					break;
				case REQUEST_CODE_TASK_3:
					mTask3TextView.setText("Task finished, result: " + result);
					break;
				default:
					Log.w(TAG, "Unknown request code: " + requestCode);
					break;
			}
		}
	}
}