package org.itstep.android.classwork.model;

/*
 * GalleryImage.java
 *
 * Created by mib on 07.11.14, 20:09
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class GalleryImage {

	private int mImageResId;

	private String mName;

	private String mDescription;

	public GalleryImage(final int imageResId, final String name, final String description) {
		mImageResId = imageResId;
		mName = name;
		mDescription = description;
	}

	public int getImageResId() {
		return mImageResId;
	}

	public String getName() {
		return mName;
	}

	public String getDescription() {
		return mDescription;
	}
}