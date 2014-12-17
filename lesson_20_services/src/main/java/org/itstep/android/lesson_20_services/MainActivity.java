package org.itstep.android.lesson_20_services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.itstep.android.lesson_20_services.service.ExtendedService;
import org.itstep.android.lesson_20_services.service.SimpleService;
import org.itstep.android.lesson_20_services.service.StickyService;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.startSimpleServiceButton).setOnClickListener(this);
		findViewById(R.id.stopSimpleServiceButton).setOnClickListener(this);
		findViewById(R.id.startExServiceButton).setOnClickListener(this);
		findViewById(R.id.stopExServiceButton).setOnClickListener(this);
		findViewById(R.id.startStickyServiceButton).setOnClickListener(this);
		findViewById(R.id.stopStickyServiceButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.startSimpleServiceButton:
				startService(new Intent(this, SimpleService.class));
				break;
			case R.id.stopSimpleServiceButton:
				stopService(new Intent(this, SimpleService.class));
				break;
			case R.id.startExServiceButton:
				startService(new Intent(this, ExtendedService.class)
						.putExtra(ExtendedService.EXTRA_KEY_TIME, 10));
				startService(new Intent(this, ExtendedService.class)
						.putExtra(ExtendedService.EXTRA_KEY_TIME, 2));
				startService(new Intent(this, ExtendedService.class)
						.putExtra(ExtendedService.EXTRA_KEY_TIME, 5));
				break;
			case R.id.stopExServiceButton:
				stopService(new Intent(this, ExtendedService.class));
				break;
			case R.id.startStickyServiceButton:
				startService(new Intent(this, StickyService.class)
						.putExtra(StickyService.EXTRA_KEY_DATA, "Test"));
				break;
			case R.id.stopStickyServiceButton:
				stopService(new Intent(this, StickyService.class));
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}