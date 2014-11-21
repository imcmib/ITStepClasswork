package org.itstep.android.lesson_15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

/*
 * SecondActivity.java
 *
 * Created by mib on 21.11.14, 20:09
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SecondActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_second);

		TextView textView = (TextView) findViewById(R.id.textView);
		final Button button = (Button) findViewById(R.id.button);

//		final Bundle extras = getIntent().getExtras();
//		if (extras != null) {
//			if (extras.containsKey(MainActivity.EXTRA_MY_KEY)) {
//				final String string = extras.getString(MainActivity.EXTRA_MY_KEY);
//				textView.setText(string);
//			}
//		}

//		final String stringExtra = getIntent().getStringExtra(MainActivity.EXTRA_MY_KEY);
//		textView.setText(stringExtra);

		final Note note = (Note) getIntent().getSerializableExtra(MainActivity.EXTRA_MY_KEY);
		textView.setText(note.getName());

		note.setName("Other note");

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				Intent intent = new Intent();
				intent.putExtra(MainActivity.EXTRA_MY_KEY, note);
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});
	}
}