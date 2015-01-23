package ort.itstep.android.lesson_27_shared_prefs;

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

		findViewById(R.id.sharedPrefsButton).setOnClickListener(this);
		findViewById(R.id.prefsActivityButton).setOnClickListener(this);
		findViewById(R.id.prefsActivityButton).setOnClickListener(this);
		findViewById(R.id.prefsNewActivity).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.sharedPrefsButton:
				SaveLoadActivity.startActivity(this);
				break;
			case R.id.prefsActivityButton:
				ReadPrefsActivity.startActivity(this);
				break;
			case R.id.prefsNewActivity:
				PrefsNewActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view: " + getResources().getResourceName(view.getId()));
				break;
		}
	}
}