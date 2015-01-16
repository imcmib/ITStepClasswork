package org.itstep.android.lesson25.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.itstep.android.lesson25.R;

/*
 * SimpleFragment.java
 *
 * Created by aivanchenko on 16.01.2015, 15:57
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SimpleFragment extends Fragment {

	public static final String EXTRA_KEY_TEXT = "EXTRA_KEY_TEXT";

	public static SimpleFragment newInstance(String text) {
		final Bundle args = new Bundle();
		args.putString(EXTRA_KEY_TEXT, text);

		final SimpleFragment fragment = new SimpleFragment();
		fragment.setArguments(args);

		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_main, container, false);

		final TextView textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(getArguments().getString(EXTRA_KEY_TEXT));

		return view;
	}
}