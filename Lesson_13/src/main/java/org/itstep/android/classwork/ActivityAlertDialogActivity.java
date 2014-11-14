package org.itstep.android.classwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/*
 * SimpleAlertDialogActivity.java
 *
 * Created by aivanchenko on 14.11.2014, 16:43
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ActivityAlertDialogActivity extends Activity implements View.OnClickListener {

	private static final String TAG = ActivityAlertDialogActivity.class.getSimpleName();

	private static final int DIALOG_ID_MESSAGE = 100;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ActivityAlertDialogActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button);

		findViewById(R.id.button).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.button:
				showDialog(DIALOG_ID_MESSAGE);
				break;
			case R.id.button2:
				showDialog(2);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	@Override
	protected Dialog onCreateDialog(final int id) {
		Log.d(TAG, "onCreateDialog: " + id);
		switch (id) {
			case DIALOG_ID_MESSAGE:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Some message")
						.setCancelable(false)
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int id) {
										dialog.cancel();
									}
								})
						.setNeutralButton("Hide",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int id) {
										dialog.cancel();
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int id) {
										dialog.cancel();
									}
								});

				final Dialog alertDialog = builder.create();
				return alertDialog;
			case 2:
				AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
				builder2.setMessage("Dialog 2");
				return builder2.create();
			default:
				return null;
		}
	}

	@Override
	protected void onPrepareDialog(final int id, final Dialog dialog) {
		Log.d(TAG, "onPrepareDialog: " + id);
		super.onPrepareDialog(id, dialog);
	}
}