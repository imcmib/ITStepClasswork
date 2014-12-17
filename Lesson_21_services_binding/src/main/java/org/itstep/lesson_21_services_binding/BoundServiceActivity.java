package org.itstep.lesson_21_services_binding;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.itstep.lesson_21_services_binding.service.BoundService;

public class BoundServiceActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = BoundServiceActivity.class.getSimpleName();

	private ServiceConnection mServiceConnection;
	private boolean mBounded;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, BoundServiceActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bound_service);

		mServiceConnection = new ServiceConnection() {
			@Override
			public void onServiceConnected(final ComponentName name, final IBinder service) {
				Log.v(TAG, "onServiceConnected: " + name);

				mBounded = true;
			}

			@Override
			public void onServiceDisconnected(final ComponentName name) {
				Log.v(TAG, "onServiceDisconnected: " + name);

				mBounded = false;
			}
		};

		findViewById(R.id.startServiceButton).setOnClickListener(this);
		findViewById(R.id.stopServiceButton).setOnClickListener(this);
		findViewById(R.id.bindServiceButton).setOnClickListener(this);
		findViewById(R.id.unbindServiceButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		final Intent intent = new Intent(this, BoundService.class);

		switch (view.getId()) {
			case R.id.startServiceButton:
				startService(intent);
				break;
			case R.id.stopServiceButton:
				stopService(intent);
				break;
			case R.id.bindServiceButton:
				bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
				break;
			case R.id.unbindServiceButton:
				if (mBounded) {
					unbindService(mServiceConnection);

					mBounded = false;
				}
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}