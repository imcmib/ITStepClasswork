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
public class ListAlertDialogActivity extends Activity implements View.OnClickListener {

	private static final String TAG = ListAlertDialogActivity.class.getSimpleName();

	private static final String[] ITEMS = {
			"Item 1",
			"Item 2",
			"Item 3"
	};

	private static final int DIALOG_ID_LIST = 100;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ListAlertDialogActivity.class);

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
				builder.setItems(ITEMS, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int item) {
						Toast.makeText(getApplicationContext(), "Your choice: " + ITEMS[item], Toast.LENGTH_SHORT).show();
					}
				});
//				builder.setMessage("Message");
				builder.setCancelable(false);
				builder.setPositiveButton("OK", null);
				return builder.create();
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