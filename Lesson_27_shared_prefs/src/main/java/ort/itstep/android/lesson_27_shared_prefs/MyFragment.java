package ort.itstep.android.lesson_27_shared_prefs;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/*
 * MyFragment.java
 *
 * Created by mib on 23.01.15, 19:36
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyFragment extends Fragment {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PreferenceManager.getDefaultSharedPreferences(getActivity());

		getActivity().getPreferences(Context.MODE_PRIVATE);

		final SharedPreferences fileName =
				getActivity().getSharedPreferences("fileName", Context.MODE_PRIVATE);
	}
}
