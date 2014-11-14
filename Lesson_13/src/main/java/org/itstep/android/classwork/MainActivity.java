package org.itstep.android.classwork;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.simpleAlertDialogButton).setOnClickListener(this);
		findViewById(R.id.activityAlertDialogButton).setOnClickListener(this);
		findViewById(R.id.listAlertDialogButton).setOnClickListener(this);
		findViewById(R.id.radioAlertDialogButton).setOnClickListener(this);
		findViewById(R.id.checkAlertDialogButton).setOnClickListener(this);
		findViewById(R.id.autoCloseAlertDialogButton).setOnClickListener(this);
		findViewById(R.id.customAlertDialogButton).setOnClickListener(this);
		findViewById(R.id.dialogsButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.simpleAlertDialogButton:
				SimpleAlertDialogActivity.startActivity(this);
				break;
			case R.id.activityAlertDialogButton:
				ActivityAlertDialogActivity.startActivity(this);
				break;
			case R.id.listAlertDialogButton:
				ListAlertDialogActivity.startActivity(this);
				break;
			case R.id.radioAlertDialogButton:
				RadioAlertDialogActivity.startActivity(this);
				break;
			case R.id.checkAlertDialogButton:
				CheckAlertDialogActivity.startActivity(this);
				break;
			case R.id.autoCloseAlertDialogButton:
				AutoCloseAlertDialogActivity.startActivity(this);
				break;
			case R.id.customAlertDialogButton:
				CustomAlertDialogActivity.startActivity(this);
				break;
			case R.id.dialogsButton:
				DialogsActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}