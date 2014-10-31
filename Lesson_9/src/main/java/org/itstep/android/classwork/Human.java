package org.itstep.android.classwork;

/*
 * Human.java
 *
 * Created by mib on 31.10.14, 20:25
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class Human {

	private String mName;

	private int mAge;

	private int mPhoto;

	public Human(final String name, final int age, final int photo) {
		mName = name;
		mAge = age;
		mPhoto = photo;
	}

	public String getName() {
		return mName;
	}

	public int getAge() {
		return mAge;
	}

	public int getPhoto() {
		return mPhoto;
	}
}
