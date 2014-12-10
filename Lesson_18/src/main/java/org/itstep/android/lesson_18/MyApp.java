package org.itstep.android.lesson_18;

import android.app.Application;
import android.content.Context;

/*
 * MyApp.java
 *
 * Created by mib on 05.12.14, 20:12
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyApp extends Application {

	private static MyApp sInstance;

	@Override
	public void onCreate() {
		super.onCreate();

		sInstance = this;
	}

	public static Context getInstance() {
		return sInstance;
	}
}
