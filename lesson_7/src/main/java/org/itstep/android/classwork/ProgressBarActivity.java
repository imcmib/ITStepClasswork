package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

/*
 * MainActivity.java
 *
 * Created by MiB on 27.10.2014, 16:36
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ProgressBarActivity extends Activity implements View.OnClickListener {

	private static final String TAG = ProgressBarActivity.class.getSimpleName();

	private ProgressBar mHorizontalProgressBar;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ProgressBarActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_progress_bar);

		mHorizontalProgressBar = (ProgressBar) findViewById(R.id.horizontalProgressBar);

		findViewById(R.id.incrementButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.incrementButton:
				final int progress = mHorizontalProgressBar.getProgress() + 1;
				mHorizontalProgressBar.setProgress(progress);

//				mHorizontalProgressBar.incrementProgressBy(1);
//				mHorizontalProgressBar.incrementSecondaryProgressBy(2);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}