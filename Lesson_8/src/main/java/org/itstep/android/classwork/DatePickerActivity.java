package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

/*
 * ImageViewActivity.java
 *
 * Created by aivanchenko on 29.10.2014, 12:49
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class DatePickerActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, DatePickerActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker);

		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		datePicker.init(2014, 9, 26, new DatePicker.OnDateChangedListener() {
			@Override
			public void onDateChanged(final DatePicker view, final int year,
									  final int monthOfYear, final int dayOfMonth) {
				final String text = String.format("%d/%d/%d", dayOfMonth, monthOfYear, year);
				Toast.makeText(DatePickerActivity.this, text, Toast.LENGTH_SHORT).show();
			}
		});
	}
}