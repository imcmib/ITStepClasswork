package org.itstep.android.lesson25.event;

/*
 * ButtonClickedEvent.java
 *
 * Created by mib on 23.01.15, 20:06
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ButtonClickedEvent {

	private String mText;

	public ButtonClickedEvent(final String text) {
		mText = text;
	}

	public String getText() {
		return mText;
	}
}
