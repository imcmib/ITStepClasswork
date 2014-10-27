package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/*
 * MainActivity.java
 *
 * Created by MiB on 27.10.2014, 16:36
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ToggleButtonActivity extends Activity implements View.OnClickListener {

	private static final String TAG = ToggleButtonActivity.class.getSimpleName();

	private ToggleButton mFirstToggleButton;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ToggleButtonActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_toggle_button);

		mFirstToggleButton = (ToggleButton) findViewById(R.id.firstToggleButton);

		final ToggleButton thirdToggleButton = (ToggleButton) findViewById(R.id.thirdToggleButton);
		thirdToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
				Log.d(TAG, getResources().getResourceName(buttonView.getId()) +  ", checked: " + isChecked);
			}
		});

		findViewById(R.id.toggleButton).setOnClickListener(this);
		findViewById(R.id.checkButton).setOnClickListener(this);
		findViewById(R.id.uncheckButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.toggleButton:
				mFirstToggleButton.toggle();
				break;
			case R.id.checkButton:
				mFirstToggleButton.setChecked(true);
				break;
			case R.id.uncheckButton:
				mFirstToggleButton.setChecked(false);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}