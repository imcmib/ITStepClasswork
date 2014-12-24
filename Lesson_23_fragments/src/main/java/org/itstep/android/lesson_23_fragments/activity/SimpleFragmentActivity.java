package org.itstep.android.lesson_23_fragments.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.itstep.android.lesson_23_fragments.R;

/*
 * SimpleFragmentActivity.java
 *
 * Created by aivanchenko on 24.12.2014, 16:32
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SimpleFragmentActivity extends Activity {

	private static final String TAG = SimpleFragmentActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SimpleFragmentActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_fragment);

		Log.d(TAG, "onCreate");
	}

	@Override
	protected void onRestoreInstanceState(final Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}

	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}

	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}

	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}
}