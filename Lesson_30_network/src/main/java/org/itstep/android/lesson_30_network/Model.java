package org.itstep.android.lesson_30_network;

import com.google.gson.annotations.SerializedName;

/*
 * Model.java
 *
 * Created by mib on 06.02.15, 19:43
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class Model {

	@SerializedName("one")
	private String mOne;

	@SerializedName("two")
	private String mTwo;

	public Model(final String one, final String key) {
		mOne = one;
		mTwo = key;
	}

	public String getOne() {
		return mOne;
	}

	public String getTwo() {
		return mTwo;
	}

	@Override
	public String toString() {
		return "Model{" +
				"mOne='" + mOne + '\'' +
				", mTwo='" + mTwo + '\'' +
				'}';
	}
}
