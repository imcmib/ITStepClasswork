package org.itstep.android.lesson_23_fragments.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.itstep.android.lesson_23_fragments.DrawerActivity;
import org.itstep.android.lesson_23_fragments.R;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.simpleFragmentsButton).setOnClickListener(this);
		findViewById(R.id.dynamicFragmentsButton).setOnClickListener(this);
		findViewById(R.id.flowButton).setOnClickListener(this);
		findViewById(R.id.drawerButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.simpleFragmentsButton:
				SimpleFragmentActivity.startActivity(this);
				break;
			case R.id.dynamicFragmentsButton:
				DynamicFragmentActivity.startActivity(this);
				break;
			case R.id.flowButton:
//				MailListActivity.startActivity(this);
				break;
			case R.id.drawerButton:
				DrawerActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}