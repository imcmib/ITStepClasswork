package org.itstep.android.lesson_28_threads.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.itstep.android.lesson_28_threads.R;

/*
 * ThreadJoinActivity.java
 *
 * Created by MiB on 29.01.2015, 19:31
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ThreadJoinActivity extends ActionBarActivity {

	private static final String TAG = ThreadJoinActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ThreadJoinActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread_join);

		findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				onStartButtonClick();
			}
		});
	}

	private void onStartButtonClick() {
		Log.d(TAG, "Thread started");

		final BackgroundThread thread = new BackgroundThread();
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Log.d(TAG, "Thread finished");
	}

	private static class BackgroundThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}