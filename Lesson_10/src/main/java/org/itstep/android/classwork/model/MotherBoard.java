package org.itstep.android.classwork.model;

/*
 * MotherBoard.java
 *
 * Created by aivanchenko on 05.11.2014, 16:44
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MotherBoard extends Item {

	private String mModel;

	public MotherBoard(final int id, final String name, String model) {
		super(id, name);

		mModel = model;
	}

	public String getModel() {
		return mModel;
	}
}