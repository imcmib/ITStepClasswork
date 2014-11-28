package org.itstep.android.lesson_17.activity;

import android.app.Activity;
import android.os.Bundle;

import org.itstep.android.lesson_17.R;

/*
 * TestActivity.java
 *
 * Created by aivanchenko on 28.11.2014, 15:31
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TestActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
	}
}