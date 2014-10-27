package org.itstep.android.classwork;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/*
 * MainActivity.java
 *
 * Created by MiB on 27.10.2014, 16:36
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MainActivity extends Activity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		findViewById(R.id.radioButtonButton).setOnClickListener(this);
		findViewById(R.id.checkBoxButton).setOnClickListener(this);
		findViewById(R.id.toggleButtonButton).setOnClickListener(this);
		findViewById(R.id.imageButtonButton).setOnClickListener(this);
		findViewById(R.id.progressBarButton).setOnClickListener(this);
		findViewById(R.id.seekBarButton).setOnClickListener(this);
		findViewById(R.id.ratingBarButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.radioButtonButton:
				RadioButtonActivity.startActivity(this);
				break;
			case R.id.checkBoxButton:
				CheckBoxActivity.startActivity(this);
				break;
			case R.id.toggleButtonButton:
				ToggleButtonActivity.startActivity(this);
				break;
			case R.id.imageButtonButton:
				ImageButtonActivity.startActivity(this);
				break;
			case R.id.progressBarButton:
				ProgressBarActivity.startActivity(this);
				break;
//			ImageView
			case R.id.seekBarButton:
				SeekBarActivity.startActivity(this);
				break;
			case R.id.ratingBarButton:
				RatingBarActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}