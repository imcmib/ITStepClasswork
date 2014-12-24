package org.itstep.android.lesson_23_fragments.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.itstep.android.lesson_23_fragments.R;

/*
 * FirstFragment.java
 *
 * Created by aivanchenko on 24.12.2014, 16:32
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class FirstFragment extends Fragment {

	private static final String TAG = FirstFragment.class.getSimpleName();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG, "onAttach");
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		return inflater.inflate(R.layout.fragment_first, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d(TAG, "onActivityCreated");
	}

	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}

	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}

	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}

	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}

	public void onDestroyView() {
		super.onDestroyView();
		Log.d(TAG, "onDestroyView");
	}

	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

	public void onDetach() {
		super.onDetach();
		Log.d(TAG, "onDetach");
	}
}