package org.itstep.android.classwork.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.itstep.android.classwork.R;

public class MainActivity extends Activity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.autoCompleteTextViewButton).setOnClickListener(this);
		findViewById(R.id.multiAutoCompleteTextViewButton).setOnClickListener(this);
		findViewById(R.id.listViewButton).setOnClickListener(this);
		findViewById(R.id.listActivityButton).setOnClickListener(this);
		findViewById(R.id.itemsAdapterButton).setOnClickListener(this);
		findViewById(R.id.componentsAdapterButton).setOnClickListener(this);
		findViewById(R.id.viewHolderAdapterButton).setOnClickListener(this);
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
			case R.id.listActivityButton:
				ListActivityActivity.startActivity(this);
				break;
			case R.id.itemsAdapterButton:
				ItemsAdapterActivity.startActivity(this);
				break;
			case R.id.componentsAdapterButton:
				ComponentsAdapterActivity.startActivity(this);
				break;
			case R.id.viewHolderAdapterButton:
				ViewHolderAdapterActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}
