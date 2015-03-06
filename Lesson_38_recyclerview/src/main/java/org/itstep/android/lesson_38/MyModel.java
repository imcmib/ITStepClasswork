package org.itstep.android.lesson_38;

/*
 * MyModel.java
 *
 * Created by MiB on 06.03.2015, 16:57
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyModel {

	private String mName;

	private String mUrl;

	public MyModel(final String name, final String url) {
		mName = name;
		mUrl = url;
	}

	public String getName() {
		return mName;
	}

	public String getUrl() {
		return mUrl;
	}
}