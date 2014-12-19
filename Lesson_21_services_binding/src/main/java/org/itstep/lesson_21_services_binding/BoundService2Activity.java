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
import android.widget.Button;

import org.itstep.lesson_21_services_binding.service.ConnectionService;
import org.itstep.lesson_21_services_binding.service.BoundService;

public class BoundService2Activity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = BoundService2Activity.class.getSimpleName();

	private ServiceConnection mServiceConnection;
	private ConnectionService mService;
	private boolean mBounded;

	private Button mUpIntervalButton;
	private Button mDownIntervalButton;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, BoundService2Activity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bound_service_2);

		mServiceConnection = new ServiceConnection() {
			@Override
			public void onServiceConnected(final ComponentName name, final IBinder service) {
				Log.v(TAG, "onServiceConnected: " + name);

				final ConnectionService.LocalBinder myBinder = (ConnectionService.LocalBinder) service;
				mService = myBinder.getService();

				mBounded = true;

				updateUI();
			}

			@Override
			public void onServiceDisconnected(final ComponentName name) {
				Log.v(TAG, "onServiceDisconnected: " + name);

				mBounded = false;

				updateUI();
			}
		};

		findViewById(R.id.startServiceButton).setOnClickListener(this);
		findViewById(R.id.stopServiceButton).setOnClickListener(this);
		findViewById(R.id.bindServiceButton).setOnClickListener(this);
		findViewById(R.id.unbindServiceButton).setOnClickListener(this);

		mUpIntervalButton = (Button) findViewById(R.id.upIntervalButton);
		mUpIntervalButton.setOnClickListener(this);

		mDownIntervalButton = (Button) findViewById(R.id.downIntervalButton);
		mDownIntervalButton.setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		final Intent intent = new Intent(this, ConnectionService.class);

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
			case R.id.upIntervalButton:
				if (mBounded) {
					mService.upInterval(500);
				}
				break;
			case R.id.downIntervalButton:
				if (mBounded) {
					mService.downInterval(500);
				}
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void updateUI() {
		mUpIntervalButton.setEnabled(mBounded);
		mDownIntervalButton.setEnabled(mBounded);
	}
}