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

		findViewById(R.id.autoCompleteTextViewButton).setOnClickListener(this);
		findViewById(R.id.multiAutoCompleteTextViewButton).setOnClickListener(this);
		findViewById(R.id.listViewButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.autoCompleteTextViewButton:
				AutoCompleteTextViewActivity.startActivity(this);
				break;
			case R.id.multiAutoCompleteTextViewButton:
				MultiAutoCompleteTextViewActivity.startActivity(this);
				break;
			case R.id.listViewButton:
				ListViewActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}
