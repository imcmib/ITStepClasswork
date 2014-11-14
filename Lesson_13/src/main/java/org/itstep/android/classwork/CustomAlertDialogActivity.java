package org.itstep.android.classwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * SimpleAlertDialogActivity.java
 *
 * Created by aivanchenko on 14.11.2014, 16:43
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class CustomAlertDialogActivity extends Activity implements View.OnClickListener {

	private static final String TAG = CustomAlertDialogActivity.class.getSimpleName();
	private static final String[] ITEMS = new String [] {
			"Item 1",
			"Item 2",
			"Item 3",
			"Item 4"
	};

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, CustomAlertDialogActivity.class);

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
		final TextView titleTextView = new TextView(this);
		titleTextView.setText("Title");

		LayoutInflater layoutInflater = LayoutInflater.from(this);
		final View messageView = layoutInflater.inflate(R.layout.custom_message_view, null);

		final LinearLayout rootLayout = (LinearLayout) messageView.findViewById(R.id.rootLayout);

		for (int i = 0; i < 20; i++) {
			Button button = new Button(this);
			button.setText("Button " + i);

			rootLayout.addView(button);
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("titleTextView");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setCustomTitle(titleTextView);
		builder.setView(messageView);
//		builder.setAdapter(new MyAdapter(this, 0, ITEMS), null);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				dialog.dismiss();
			}
		});

		final AlertDialog alert = builder.create();
		alert.show();
	}
}