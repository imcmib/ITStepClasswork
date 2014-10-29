package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextClock;

/*
 * ImageViewActivity.java
 *
 * Created by aivanchenko on 29.10.2014, 12:49
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TextClockActivity extends Activity implements View.OnClickListener {

	private static final String TAG = TextClockActivity.class.getSimpleName()
;
	private TextClock mTextClock;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, TextClockActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_clock);

		mTextClock = (TextClock) findViewById(R.id.textClock);

		findViewById(R.id.format12Button).setOnClickListener(this);
		findViewById(R.id.format24Button).setOnClickListener(this);
		findViewById(R.id.formatCustomButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.format12Button:
				mTextClock.setFormat12Hour(TextClock.DEFAULT_FORMAT_12_HOUR);
				break;
			case R.id.format24Button:
				mTextClock.setFormat12Hour(TextClock.DEFAULT_FORMAT_24_HOUR);
				break;
			case R.id.formatCustomButton:
				mTextClock.setFormat12Hour("hh:mm:ss");
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}