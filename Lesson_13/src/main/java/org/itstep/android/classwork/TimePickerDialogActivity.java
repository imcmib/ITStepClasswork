package org.itstep.android.classwork;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

/*
 * SimpleAlertDialogActivity.java
 *
 * Created by aivanchenko on 14.11.2014, 16:43
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TimePickerDialogActivity extends Activity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

	private static final String TAG = TimePickerDialogActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, TimePickerDialogActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button);

		findViewById(R.id.button).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.button:
				showAlertDialog();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void showAlertDialog() {
		final int hour = 20;
		final int minute = 30;

		final TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, hour, minute, true);
		timePickerDialog.show();
	}

	@Override
	public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
		final String date = String.format("%d:%d", hourOfDay, minute);
		Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
	}
}