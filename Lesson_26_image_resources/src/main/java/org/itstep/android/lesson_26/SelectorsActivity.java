package org.itstep.android.lesson_26;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/*
 * ShapesActivity.java
 *
 * Created by aivanchenko on 21.01.2015, 17:08
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SelectorsActivity extends ActionBarActivity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SelectorsActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectors);
	}
}