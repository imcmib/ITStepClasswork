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
		setContentView(org.itstep.lesson_21_services_binding.R.layout.activity_main);

		findViewById(org.itstep.lesson_21_services_binding.R.id.pendingIntentResultButton).setOnClickListener(this);
		findViewById(org.itstep.lesson_21_services_binding.R.id.broadcastResultButton).setOnClickListener(this);
		findViewById(org.itstep.lesson_21_services_binding.R.id.boundServiceButton).setOnClickListener(this);
		findViewById(org.itstep.lesson_21_services_binding.R.id.intentServiceButton).setOnClickListener(this);
		findViewById(org.itstep.lesson_21_services_binding.R.id.foregroundServiceButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case org.itstep.lesson_21_services_binding.R.id.pendingIntentResultButton:
				PendingIntentResultActivity.startActivity(this);
				break;
			case org.itstep.lesson_21_services_binding.R.id.broadcastResultButton:
				BroadcastResultActivity.startActivity(this);
				break;
			case org.itstep.lesson_21_services_binding.R.id.boundServiceButton:
				BoundServiceActivity.startActivity(this);
				break;
			case org.itstep.lesson_21_services_binding.R.id.intentServiceButton:
				IntentServiceActivity.startActivity(this);
				break;
			case org.itstep.lesson_21_services_binding.R.id.foregroundServiceButton:
				ForegroundServiceActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}