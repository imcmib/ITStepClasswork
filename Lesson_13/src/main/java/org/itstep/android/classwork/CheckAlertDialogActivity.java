package org.itstep.android.classwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
public class CheckAlertDialogActivity extends Activity implements View.OnClickListener {

	private static final String TAG = CheckAlertDialogActivity.class.getSimpleName();

	private static final String[] ITEMS = {
			"Item 1",
			"Item 2",
			"Item 3"
	};

	private static final int DIALOG_ID_LIST = 100;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, CheckAlertDialogActivity.class);

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
				showDialog(DIALOG_ID_LIST);
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
			case DIALOG_ID_LIST:
				final AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Select item");
				final boolean[] selected = new boolean[] {
					false, true, false
				};
				builder.setMultiChoiceItems(ITEMS, selected, new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(final DialogInterface dialog, final int which, final boolean isChecked) {
						Toast.makeText(getApplicationContext(), String.format("%s - %s", ITEMS[which], isChecked), Toast.LENGTH_SHORT).show();
						selected[which] = isChecked;
					}
				});
				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(final DialogInterface dialog, final int which) {
						StringBuilder message= new StringBuilder();
						for (int i = 0; i < selected.length; i++) {
							message.append(ITEMS[i] + " " + selected[i] + "\n");
						}
						Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
					}
				});
				return builder.create();
			default:
				return null;
		}
	}
}