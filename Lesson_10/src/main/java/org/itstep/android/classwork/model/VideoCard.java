package org.itstep.android.classwork.model;

/*
 * VideoCard.java
 *
 * Created by aivanchenko on 05.11.2014, 16:45
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class VideoCard extends Item {

	private int mRam;

	public VideoCard(final int id, final String name, int ram) {
		super(id, name);

		mRam = ram;
	}

	public int getRam() {
		return mRam;
	}
}