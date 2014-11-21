package org.itstep.android.lesson_15;

import java.io.Serializable;

/*
 * Note.java
 *
 * Created by mib on 21.11.14, 20:34
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class Note implements Serializable {

	private String mName;

	public Note(final String name) {
		mName = name;
	}

	public String getName() {
		return mName;
	}

	public void setName(final String name) {
		mName = name;
	}
}
