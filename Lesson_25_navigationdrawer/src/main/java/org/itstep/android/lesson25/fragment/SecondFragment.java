package org.itstep.android.lesson25.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.itstep.android.lesson25.MyApp;
import org.itstep.android.lesson25.R;
import org.itstep.android.lesson25.event.ButtonClickedEvent;
import org.itstep.android.lesson25.event.SecondEvent;

/*
 * SimpleFragment.java
 *
 * Created by aivanchenko on 16.01.2015, 15:57
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SecondFragment extends Fragment {

	public static final String EXTRA_KEY_TEXT = "EXTRA_KEY_TEXT";
	private static final String TAG = SecondFragment.class.getSimpleName();

	public static SecondFragment newInstance(String text) {
		final Bundle args = new Bundle();
		args.putString(EXTRA_KEY_TEXT, text);

		final SecondFragment fragment = new SecondFragment();
		fragment.setArguments(args);

		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_main, container, false);

		final TextView textView = (TextView) view.findViewById(R.id.textView);
		final String text = getArguments().getString(EXTRA_KEY_TEXT);
		textView.setText(text);

		MyApp.getInstance().getBus().register(this);

		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		MyApp.getInstance().getBus().unregister(this);
	}

	@Subscribe
	public void onButtonClicked(ButtonClickedEvent event) {
		Log.v(TAG, "Clicked");
	}
}