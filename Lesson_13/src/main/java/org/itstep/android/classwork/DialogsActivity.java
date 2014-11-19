package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;

public class DialogsActivity extends Activity implements View.OnClickListener {

	private static final String TAG = DialogsActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, DialogsActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialogs);

		findViewById(R.id.datePickerDialog).setOnClickListener(this);
		findViewById(R.id.timePickerDialog).setOnClickListener(this);
		findViewById(R.id.progressDialog).setOnClickListener(this);
		findViewById(R.id.characterPickerDialog).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.datePickerDialog:
				DatePickerDialogActivity.startActivity(this);
				break;
			case R.id.timePickerDialog:
				TimePickerDialogActivity.startActivity(this);
				break;
			case R.id.progressDialog:
				ProgressDialogActivity.startActivity(this);
				break;
			case R.id.characterPickerDialog:
				CharacterPickerDialogActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}