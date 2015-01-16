package org.itstep.android.lesson_25_example.fragment;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.itstep.android.lesson_25_example.R;
import org.itstep.android.lesson_25_example.model.Note;

import java.util.ArrayList;
import java.util.List;

/*
 * DrawerFragment.java
 *
 * Created by mib on 16.01.15, 20:52
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class DrawerFragment extends ListFragment {

	private ArrayAdapter<String> mAdapter;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final List<String> notes = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			notes.add(String.format("Page %d", i));
		}

		mAdapter = new ArrayAdapter<>(
				getActivity(),
				R.layout.list_item_note,
				R.id.titleTextView,
				notes);

		setListAdapter(mAdapter);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View view = super.onCreateView(inflater, container, savedInstanceState);
		view.setBackgroundColor(Color.WHITE);
		return view;
	}
}