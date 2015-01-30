package org.itstep.android.lesson_28_threads.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.itstep.android.lesson_28_threads.R;
import org.itstep.android.lesson_28_threads.thread.SimpleThread;

import java.util.concurrent.TimeUnit;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button_threads_1).setOnClickListener(this);
		findViewById(R.id.button_threads_2).setOnClickListener(this);
		findViewById(R.id.button_threads_3).setOnClickListener(this);
		findViewById(R.id.button_threads_4).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.button_threads_1:
				FirstExampleActivity.startActivity(this);
				break;
			case R.id.button_threads_2:
				ThreadJoinActivity.startActivity(this);
				break;
			case R.id.button_threads_3:
				ThreadSyncActivity.startActivity(this);
				break;
			case R.id.button_threads_4:
				AsyncActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}