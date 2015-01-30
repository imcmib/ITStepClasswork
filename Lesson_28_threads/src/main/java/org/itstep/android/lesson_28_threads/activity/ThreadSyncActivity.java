package org.itstep.android.lesson_28_threads.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import org.itstep.android.lesson_28_threads.R;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/*
 * ThreadSyncActivity.java
 *
 * Created by MiB on 29.01.2015, 19:49
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ThreadSyncActivity extends ActionBarActivity {

	private static final String TAG = ThreadSyncActivity.class.getSimpleName();

	private final Object mLock = new Object();

	private volatile Integer mCounter;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ThreadSyncActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread_sync);

		findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				onStartButtonClick();
			}
		});
	}

	private void onStartButtonClick() {
		mCounter = 0;

		final MyThread thread1 = new MyThread(this, true);
		final MyThread thread2 = new MyThread(this, false);

		final long startTime = System.nanoTime();

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		final long finishTime = System.nanoTime();
		final long time = finishTime - startTime;

		final TextView textView = (TextView) findViewById(R.id.textView);
		textView.setText(String.format("Value: %d, time: %dms",
				mCounter, TimeUnit.NANOSECONDS.toMillis(time)));
	}

	public static synchronized void main() {}

	public void updateCounter(final int offset) {
		mCounter += offset;
	}

	private static class MyThread extends Thread {

		private final ThreadSyncActivity mActivity;
		private final boolean mIncrement;

		private MyThread(ThreadSyncActivity activity, boolean increment) {
			mActivity = activity;
			mIncrement = increment;
		}

		@Override
		public void run() {
			for (int i = 0; i < 100000; i++) {
				mActivity.updateCounter(mIncrement ? 1 : -1);
			}
		}
	}
}