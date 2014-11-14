package org.itstep.android.classwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/*
 * SimpleAlertDialogActivity.java
 *
 * Created by aivanchenko on 14.11.2014, 16:43
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SimpleAlertDialogActivity extends Activity implements View.OnClickListener,
		DialogInterface.OnClickListener {

	private static final String TAG = SimpleAlertDialogActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SimpleAlertDialogActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button);

		findViewById(R.id.button).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.button:
				showAlertDialog();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void showAlertDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Some title")
				.setMessage("Some message")
				.setIcon(R.drawable.ic_launcher)
				.setCancelable(true)
				.setNegativeButton("Close", this)
				.setNeutralButton("Hide", this)
				.setPositiveButton("Ok", this)
				.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(final DialogInterface dialog) {
						Toast.makeText(SimpleAlertDialogActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
						Log.d(TAG, "onCancel");
						//						dialog.dismiss();
					}
				})
				.setOnDismissListener(new DialogInterface.OnDismissListener() {
					@Override
					public void onDismiss(final DialogInterface dialog) {
						Log.d(TAG, "onDismiss");
					}
				});

		final AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		final String button;
		switch (which) {
			case AlertDialog.BUTTON_POSITIVE:
				button = "Positive";
				break;
			case AlertDialog.BUTTON_NEGATIVE:
				button = "Negative";
				break;
			default:
				button = "Neutral";
				break;
		}
		Toast.makeText(this, button, Toast.LENGTH_SHORT).show();
	}
}