package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*
 * MainActivity.java
 *
 * Created by MiB on 27.10.2014, 16:36
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class RadioButtonActivity extends Activity implements View.OnClickListener {

	private static final String TAG = RadioButtonActivity.class.getSimpleName();

	private RadioGroup mFirstRadioGroup;
	private RadioButton mFirstRadioButton;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, RadioButtonActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_radio_button);

		mFirstRadioGroup = (RadioGroup) findViewById(R.id.firstRadioGroup);
		mFirstRadioGroup.setOnCheckedChangeListener(
				new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(final RadioGroup group, final int checkedId) {
				final View viewById = group.findViewById(checkedId);
				if (viewById != null && viewById instanceof RadioButton) {
					final RadioButton radioButton = (RadioButton) viewById;
					Log.d(TAG, "RadioGroup: " + getResources().getResourceName(checkedId)
							+ ", checked: " + radioButton.isChecked());
				}
			}
		});

		mFirstRadioButton = (RadioButton) findViewById(R.id.firstRadioButton);
		mFirstRadioButton.setOnCheckedChangeListener(
				new RadioButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(final CompoundButton compoundButton,
												 final boolean isChecked) {
						Log.d(TAG, "RadioButton: " + getResources().getResourceName(compoundButton.getId())
						+ ", checked: " + isChecked);
					}
				});

		findViewById(R.id.toggleButton).setOnClickListener(this);
		findViewById(R.id.checkButton).setOnClickListener(this);
		findViewById(R.id.uncheckButton).setOnClickListener(this);
		findViewById(R.id.clearButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.toggleButton:
				mFirstRadioButton.toggle();
				break;
			case R.id.checkButton:
				mFirstRadioButton.setChecked(true);
				break;
			case R.id.uncheckButton:
				mFirstRadioButton.setChecked(false);
				break;
			case R.id.clearButton:
				mFirstRadioGroup.clearCheck();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}