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
import org.itstep.android.lesson_23_fragments.fragment.ThirdFragment;

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
	private ThirdFragment mThirdFragment;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, DynamicFragmentActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_fragment);

		findViewById(R.id.addButton1).setOnClickListener(this);
		findViewById(R.id.addButton2).setOnClickListener(this);
		findViewById(R.id.addButton3).setOnClickListener(this);
		findViewById(R.id.removeButton).setOnClickListener(this);
		findViewById(R.id.replaceButton).setOnClickListener(this);

		mAddToBackStackCheckBox = (CheckBox) findViewById(R.id.addToBackStackCheckBox);

		mFirstFragment = new FirstFragment();
		mFirstFragment.setRetainInstance(true);
		mFirstFragment.setHasOptionsMenu(true);

		final Bundle args = new Bundle();
		mFirstFragment.setArguments(args);

		mSecondFragment = new SecondFragment();
		mThirdFragment = new ThirdFragment();
	}

	@Override
	protected void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString("key", "value");
	}

	@Override
	public void onClick(final View view) {
		final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

		switch (view.getId()) {
			case R.id.addButton1:
				fragmentTransaction.add(R.id.frameLayout, mFirstFragment);
				break;
			case R.id.addButton2:
				fragmentTransaction.add(R.id.frameLayout, mSecondFragment);
				break;
			case R.id.addButton3:
				fragmentTransaction.add(R.id.frameLayout, mThirdFragment);
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