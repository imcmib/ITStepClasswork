package org.itstep.lesson_21_services_binding;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.pendingIntentResultButton).setOnClickListener(this);
		findViewById(R.id.broadcastResultButton).setOnClickListener(this);
		findViewById(R.id.intentServiceButton).setOnClickListener(this);
		findViewById(R.id.foregroundServiceButton).setOnClickListener(this);
		findViewById(R.id.boundServiceButton).setOnClickListener(this);
		findViewById(R.id.boundService2Button).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.pendingIntentResultButton:
				PendingIntentResultActivity.startActivity(this);
				break;
			case R.id.broadcastResultButton:
				BroadcastResultActivity.startActivity(this);
				break;
			case R.id.boundServiceButton:
				BoundServiceActivity.startActivity(this);
				break;
			case R.id.intentServiceButton:
				IntentServiceActivity.startActivity(this);
				break;
			case R.id.foregroundServiceButton:
				ForegroundServiceActivity.startActivity(this);
				break;
			case R.id.boundService2Button:
				BoundService2Activity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}