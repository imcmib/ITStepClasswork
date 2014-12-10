package org.itstep.android.lesson19;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/*
 * SimpleReceiverActivity.java
 *
 * Created by aivanchenko on 10.12.2014, 15:50
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SimpleReceiverActivity extends Activity implements View.OnClickListener {

	private static final String TAG = SimpleReceiverActivity.class.getSimpleName();

	public static final String ACTION_MY_SIMPLE = BuildConfig.APPLICATION_ID + ".ACTION_MY_SIMPLE";

	public static final String EXTRA_KEY_TEXT = "EXTRA_KEY_TEXT";

	private IntentFilter mIntentFilter;
	private MyBroadcastReceiver mReceiver;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SimpleReceiverActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_receiver);

		findViewById(R.id.sendBroadcastButton).setOnClickListener(this);

		mIntentFilter = new IntentFilter(ACTION_MY_SIMPLE);
		mReceiver = new MyBroadcastReceiver();
	}

	@Override
	protected void onResume() {
		super.onResume();

		registerReceiver(mReceiver, mIntentFilter);
	}

	@Override
	protected void onPause() {
		super.onPause();

		unregisterReceiver(mReceiver);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.sendBroadcastButton:
				final Intent intent = new Intent(ACTION_MY_SIMPLE);
				intent.putExtra(EXTRA_KEY_TEXT, "Testing broadcast receivers!");
				sendBroadcast(intent);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private static class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(final Context context, final Intent intent) {
			if (intent.getAction().equals(ACTION_MY_SIMPLE)) {
				final String text = intent.getStringExtra(EXTRA_KEY_TEXT);
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
			}
		}
	}
}