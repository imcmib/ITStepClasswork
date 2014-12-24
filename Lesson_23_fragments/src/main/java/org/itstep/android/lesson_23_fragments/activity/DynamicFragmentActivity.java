package org.itstep.android.lesson_23_fragments.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import org.itstep.android.lesson_23_fragments.R;
import org.itstep.android.lesson_23_fragments.fragment.FirstFragment;
import org.itstep.android.lesson_23_fragments.fragment.SecondFragment;

/*
 * SimpleFragmentActivity.java
 *
 * Created by aivanchenko on 24.12.2014, 16:32
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class DynamicFragmentActivity extends Activity implements View.OnClickListener {

	private static final String TAG = DynamicFragmentActivity.class.getSimpleName();

	private FirstFragment mFirstFragment;
	private SecondFragment mSecondFragment;

	private CheckBox mAddToBackStackCheckBox;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, DynamicFragmentActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_fragment);

		findViewById(R.id.addButton).setOnClickListener(this);
		findViewById(R.id.removeButton).setOnClickListener(this);
		findViewById(R.id.replaceButton).setOnClickListener(this);

		mAddToBackStackCheckBox = (CheckBox) findViewById(R.id.addToBackStackCheckBox);

		mFirstFragment = new FirstFragment();
		mSecondFragment = new SecondFragment();
	}

	@Override
	public void onClick(final View view) {
		final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

		switch (view.getId()) {
			case R.id.addButton:
				fragmentTransaction.add(R.id.frameLayout, mFirstFragment);
				break;
			case R.id.removeButton:
				fragmentTransaction.remove(mFirstFragment);
				break;
			case R.id.replaceButton:
				fragmentTransaction.replace(R.id.frameLayout, mSecondFragment);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}

		if (mAddToBackStackCheckBox.isChecked()) {
			fragmentTransaction.addToBackStack(null);
		}

		fragmentTransaction.commit();
	}
}