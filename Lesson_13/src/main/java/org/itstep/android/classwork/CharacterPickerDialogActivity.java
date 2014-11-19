package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/*
 * SimpleAlertDialogActivity.java
 *
 * Created by aivanchenko on 14.11.2014, 16:43
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class CharacterPickerDialogActivity extends Activity implements View.OnClickListener {

	private static final String TAG = CharacterPickerDialogActivity.class.getSimpleName();

	private EditText mEditText;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, CharacterPickerDialogActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_picker);

		mEditText = (EditText) findViewById(R.id.editText);

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
		final View view = new View(this);
		final Editable editable = mEditText.getText();
		final String options = "0123456789ABCDEF";
		final CharacterPickerDialog dialog = new CharacterPickerDialog(this, view, editable, options, true);
		dialog.show();
	}
}