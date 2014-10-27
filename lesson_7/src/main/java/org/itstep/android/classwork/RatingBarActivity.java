package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

/*
 * MainActivity.java
 *
 * Created by MiB on 27.10.2014, 16:36
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class RatingBarActivity extends Activity implements View.OnClickListener {

	private static final String TAG = RatingBarActivity.class.getSimpleName();
	private RatingBar mRatingBar;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, RatingBarActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_rating_bar);

		final TextView progressTextView = (TextView) findViewById(R.id.progressTextView);

		mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
		mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(final RatingBar ratingBar, final float v, final boolean b) {
				progressTextView.setText(String.format("Stars: %f, %s", v, String.valueOf(b)));
			}
		});

		findViewById(R.id.button).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.button:
				mRatingBar.setProgress(3);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}