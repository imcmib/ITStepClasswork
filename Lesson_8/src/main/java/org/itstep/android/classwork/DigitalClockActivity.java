package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DigitalClock;

/*
 * ImageViewActivity.java
 *
 * Created by aivanchenko on 29.10.2014, 12:49
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class DigitalClockActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, DigitalClockActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_digital_clock);

		findViewById(R.id.setTextButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				DigitalClock digitalClock = (DigitalClock) findViewById(R.id.digitalClock);
				digitalClock.setText("Some text");
			}
		});
	}
}