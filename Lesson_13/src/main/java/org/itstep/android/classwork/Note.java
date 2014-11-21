package org.itstep.android.classwork;

/*
 * Note.java
 *
 * Created by mib on 19.11.14, 20:35
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class Note {

	private String mName;

	private String mDescription;

	public Note(final String name, final String description) {
		mName = name;
		mDescription = description;
	}

	public String getName() {
		return mName;
	}

	public void setName(final String name) {
		mName = name;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(final String description) {
		mDescription = description;
	}
}