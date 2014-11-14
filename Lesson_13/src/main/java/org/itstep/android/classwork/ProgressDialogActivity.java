package org.itstep.android.classwork;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
public class ProgressDialogActivity extends Activity implements View.OnClickListener {

	private static final String TAG = ProgressDialogActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ProgressDialogActivity.class);

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
		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Loading...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMax(100);
		progressDialog.show();

		final Handler handler = new Handler();
		final Runnable runnable = new Runnable() {

			public int mProgress;

			@Override
			public void run() {
				progressDialog.setProgress(mProgress++);

				handler.postDelayed(this, 100);
			}
		};
		handler.postDelayed(runnable, 100);
	}
}