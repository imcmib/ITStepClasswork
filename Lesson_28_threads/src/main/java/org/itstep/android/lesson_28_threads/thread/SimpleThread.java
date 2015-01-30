package org.itstep.android.lesson_28_threads.thread;

import android.util.Log;

/*
 * CounterThread.java
 *
 * Created by MiB on 29.01.2015, 18:39
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SimpleThread extends Thread {

	private static final String TAG = SimpleThread.class.getSimpleName();

	private int mCounter;

	@Override
	public void run() {
		mCounter = 0;

		while (true) {
			if (Thread.interrupted()) {
				Log.d(TAG, getName() + " interrupted");
				break;
			}

//			Log.d(TAG, String.format("%s: %d", getName(), mCounter));

			mCounter++;
		}
	}

	public int getCounter() {
		return mCounter;
	}
}