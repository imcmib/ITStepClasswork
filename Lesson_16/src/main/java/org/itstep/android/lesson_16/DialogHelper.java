package org.itstep.android.lesson_16;

import android.app.AlertDialog;
import android.content.Context;

/*
 * DialogHelper.java
 *
 * Created by MiB on 26.11.2014, 16:30
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class DialogHelper {

	private DialogHelper() {
		// No instance
	}

	public static void showMessageDialog(final Context context, final String title, final String message) {
		final AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(android.R.string.ok, null)
				.create();
		dialog.show();
	}
}