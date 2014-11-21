package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
	private Button mButton;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ProgressDialogActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button);

		mButton = (Button) findViewById(R.id.button);
		mButton.setOnClickListener(this);
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
//		final ProgressDialog progressDialog = new ProgressDialog(this);
//		progressDialog.setMessage("Loading...");
//		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//		progressDialog.setMax(100);
//		progressDialog.show();
//
//		final Handler handler = new Handler(Looper.getMainLooper());
//		final Runnable runnable = new Runnable() {
//
//			public int mProgress;
//
//			@Override
//			public void run() {
//				progressDialog.setProgress(mProgress++);
//
//				if (mProgress < 100) {
//					handler.postDelayed(this, 100);
//				}
//			}
//		};
//		handler.postDelayed(runnable, 100);

		new Thread(new Runnable() {
			@Override
			public void run() {
//				final Handler handler = new Handler(Looper.getMainLooper());
//				handler.post(new Runnable() {
//					@Override
//					public void run() {
//						mButton.setText("TEST");
//					}
//				});

//				mButton.post(new Runnable() {
//					@Override
//					public void run() {
//						mButton.setText("TEST");
//					}
//				});
			}
		}).start();
	}
}