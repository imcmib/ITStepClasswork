package org.itstep.lesson_21_services_binding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.itstep.lesson_21_services_binding.service.MyIntentService;

/*
 * PendingIntentResultActivity.java
 *
 * Created by aivanchenko on 17.12.2014, 12:48
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class IntentServiceActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, IntentServiceActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_service);

		findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				onStartButtonClick();
			}
		});
	}

	private void onStartButtonClick() {
		launchService("Task 1", 10);
		launchService("Task 2", 5);
		launchService("Task 3", 7);
	}

	private void launchService(String label, int time) {
		final Intent intent = new Intent(this, MyIntentService.class)
				.putExtra(MyIntentService.EXTRA_KEY_LABEL, label)
				.putExtra(MyIntentService.EXTRA_KEY_TIME, time);

		startService(intent);
	}
}