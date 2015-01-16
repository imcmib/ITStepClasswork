package org.itstep.android.lesson_25_example.model;

import java.io.Serializable;

/*
 * Note.java
 *
 * Created by mib on 16.01.15, 19:58
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class Note implements Serializable {

	private long mId;

	private String mTitle;

	public Note(final long id, final String title) {
		mId = id;
		mTitle = title;
	}

	public long getId() {
		return mId;
	}

	public String getTitle() {
		return mTitle;
	}

	@Override
	public String toString() {
		return mTitle;
	}
}
