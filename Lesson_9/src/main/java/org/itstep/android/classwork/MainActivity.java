package org.itstep.android.classwork;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.simpleAdapterButton).setOnClickListener(this);
		findViewById(R.id.arrayAdapterButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.simpleAdapterButton:
				SimpleAdapterActivity.startActivity(this);
				break;
			case R.id.arrayAdapterButton:
				ArrayAdapterActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}