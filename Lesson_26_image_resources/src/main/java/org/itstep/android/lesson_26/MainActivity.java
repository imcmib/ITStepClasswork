package org.itstep.android.lesson_26;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

/*
 * MainActivity.java
 *
 * Created by aivanchenko on 21.01.2015, 16:28
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.ninePatchButton).setOnClickListener(this);
		findViewById(R.id.shapesButton).setOnClickListener(this);
		findViewById(R.id.selectorsButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.ninePatchButton:
				NinePatchActivity.startActivity(this);
				break;
			case R.id.shapesButton:
				ShapesActivity.startActivity(this);
				break;
			case R.id.selectorsButton:
				SelectorsActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view: " + getResources().getResourceName(view.getId()));
				break;
		}
	}
}