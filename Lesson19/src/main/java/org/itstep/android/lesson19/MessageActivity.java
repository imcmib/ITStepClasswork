package org.itstep.android.lesson19;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.itstep.android.lesson19.receiver.SimpleReceiver;

/*
 * MessageActivity.java
 *
 * Created by aivanchenko on 10.12.2014, 16:33
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MessageActivity extends Activity implements View.OnClickListener {

	private static final String TAG = MessageActivity.class.getSimpleName();

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		final Bundle extras = getIntent().getExtras();
		if (extras != null) {
			final TextView messageTextView = (TextView) findViewById(R.id.messageTextView);
			messageTextView.setText(extras.getString(SimpleReceiver.EXTRA_KEY_MESSAGE, ""));
		}

		findViewById(R.id.closeButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.closeButton:
				finish();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}