package org.itstep.android.lesson_28_threads.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.itstep.android.lesson_28_threads.R;

/*
 * AsyncActivity.java
 *
 * Created by mib on 30.01.15, 20:04
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class AsyncActivity extends ActionBarActivity {

	private static final String TAG = AsyncActivity.class.getSimpleName();
	private TextView mTextView;
	private AsyncTask<Long, Integer, Boolean> mAsyncTask;

	public static void startActivity(Activity context) {
		final Intent intent = new Intent(context, AsyncActivity.class);

		context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async);

		mTextView = (TextView) findViewById(R.id.textView);
		findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				startAsyncTask();
			}
		});
		findViewById(R.id.button_stop).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				mAsyncTask.cancel(true);
			}
		});
	}

	private void startAsyncTask() {
		mAsyncTask = new AsyncTask<Long, Integer, Boolean>() {
			@Override
			protected void onPreExecute() {
				mTextView.setText("Thread started!");
			}

			@Override
			protected Boolean doInBackground(final Long[] params) {
				long count = params[0];
				for (int i = 0; i < count; i++) {
					if (isCancelled()) {
						return false;
					}

					Log.v(TAG, "Count: " + i);

					publishProgress(i);

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				return true;
			}

			@Override
			protected void onProgressUpdate(final Integer... values) {
				mTextView.setText(String.valueOf(values[0]));
			}

			@Override
			protected void onPostExecute(final Boolean result) {
				mTextView.setText(String.valueOf(result));
			}

			@Override
			protected void onCancelled() {
				Log.w(TAG, "Cancelled without result");
			}

			@Override
			protected void onCancelled(final Boolean aBoolean) {
				Log.w(TAG, "Cancelled with result: " + aBoolean);
			}
		};

		mAsyncTask.execute(20l);
	}
}
