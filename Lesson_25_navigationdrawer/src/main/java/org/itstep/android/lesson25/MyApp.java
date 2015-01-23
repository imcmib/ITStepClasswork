package org.itstep.android.lesson25;

import android.app.Application;

import com.squareup.otto.Bus;

/*
 * MyApp.java
 *
 * Created by mib on 23.01.15, 19:55
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyApp extends Application {

	private Bus mBus;

	private static MyApp sInstance;

	@Override
	public void onCreate() {
		super.onCreate();

		sInstance = this;

		mBus = new Bus();
	}

	public static MyApp getInstance() {
		return sInstance;
	}

	public Bus getBus() {
		return mBus;
	}
}