package org.itstep.android.lesson19;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/*
 * BatteryActivity.java
 *
 * Created by mib on 10.12.14, 19:45
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class BatteryActivity extends Activity {

	private BatteryReceiver mBatteryReceiver;
	private TextView mBatteryLevelTextView;
	private ProgressBar mProgressBar;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, BatteryActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battery);

		mBatteryLevelTextView = (TextView) findViewById(R.id.batteryLevelTextView);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

		mBatteryReceiver = new BatteryReceiver();

		final IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		filter.addAction(Intent.ACTION_POWER_CONNECTED);
		filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
		filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");

		registerReceiver(mBatteryReceiver, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		unregisterReceiver(mBatteryReceiver);
	}

	private class BatteryReceiver extends BroadcastReceiver {

		private final String TAG = BatteryReceiver.class.getSimpleName();

		@Override
		public void onReceive(final Context context, final Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
				final Bundle extras = intent.getExtras();
				for (String key : extras.keySet()) {
					Log.v(TAG, "Key: " + key + ": " + extras.get(key));
				}

				int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
				int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

				mBatteryLevelTextView.setText(
						context.getString(R.string.label_battery_level_d, level));

				mProgressBar.setMax(scale);
				mProgressBar.setProgress(level);
			} else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
				mBatteryLevelTextView.setTextColor(Color.GREEN);
			} else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
				mBatteryLevelTextView.setTextColor(Color.RED);
			} else if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
				final String stateStr;
				final int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
						WifiManager.WIFI_STATE_UNKNOWN);
				switch (state) {
					case WifiManager.WIFI_STATE_DISABLED:
						stateStr = "WIFI_STATE_DISABLED";
						break;
					case WifiManager.WIFI_STATE_DISABLING:
						stateStr = "WIFI_STATE_DISABLING";
						break;
					case WifiManager.WIFI_STATE_ENABLED:
						stateStr = "WIFI_STATE_ENABLED";
						break;
					case WifiManager.WIFI_STATE_ENABLING:
						stateStr = "WIFI_STATE_ENABLING";
						break;
					case WifiManager.WIFI_STATE_UNKNOWN:
					default:
						stateStr = "WIFI_STATE_UNKNOWN";
						break;

				}
				Toast.makeText(context, stateStr, Toast.LENGTH_SHORT).show();
			}
		}
	}
}
