package org.itstep.lesson_21_services_binding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ForegroundServiceActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(org.itstep.lesson_21_services_binding.R.layout.activity_foreground_service);

		findViewById(org.itstep.lesson_21_services_binding.R.id.startServiceButton).setOnClickListener(this);
		findViewById(org.itstep.lesson_21_services_binding.R.id.stopServiceButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case org.itstep.lesson_21_services_binding.R.id.startServiceButton:
				startService(new Intent(this, ForegroundService.class));
				break;
			case org.itstep.lesson_21_services_binding.R.id.stopServiceButton:
				stopService(new Intent(this, ForegroundService.class));
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}