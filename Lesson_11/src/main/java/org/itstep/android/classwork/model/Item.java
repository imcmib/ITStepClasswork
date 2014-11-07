package org.itstep.android.classwork.model;

/*
 * Item.java
 *
 * Created by aivanchenko on 07.11.2014, 16:49
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class Item {

	private int mId;

	private String mName;

	public Item(final int id, final String name) {
		mId = id;
		mName = name;
	}

	public int getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}
}