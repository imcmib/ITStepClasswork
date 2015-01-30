package org.itstep.android.lesson_28_threads.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.itstep.android.lesson_28_threads.R;
import org.itstep.android.lesson_28_threads.thread.SimpleThread;

/*
 * FirstExample.java
 *
 * Created by MiB on 29.01.2015, 18:46
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class FirstExampleActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = FirstExampleActivity.class.getSimpleName();

	private SimpleThread mThread1;
	private SimpleThread mThread2;

	private TextView mTextView1;
	private TextView mTextView2;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, FirstExampleActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_example);

		findViewById(R.id.button_start).setOnClickListener(this);
		findViewById(R.id.button_stop).setOnClickListener(this);

		mTextView1 = (TextView) findViewById(R.id.textView1);
		mTextView2 = (TextView) findViewById(R.id.textView2);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.button_start:
				startThreads();
				break;
			case R.id.button_stop:
				stopThreads();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void startThreads() {
		mThread1 = new SimpleThread();
		mThread1.setName("Thread 1");
		mThread1.setPriority(Thread.MIN_PRIORITY);
		mThread1.start();

		mThread2 = new SimpleThread();
		mThread2.setName("Thread 2");
		mThread2.setPriority(Thread.MAX_PRIORITY);
		mThread2.start();
	}

	private void stopThreads() {
		mThread1.interrupt();
		mThread2.interrupt();

//		try {
//			mThread1.join();
//			mThread2.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		mTextView1.setText(String.format("Thread 1: " + mThread1.getCounter()));
		mTextView2.setText(String.format("Thread 2: " + mThread2.getCounter()));
	}
}