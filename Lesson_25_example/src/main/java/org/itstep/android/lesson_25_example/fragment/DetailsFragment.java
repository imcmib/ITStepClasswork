package org.itstep.android.lesson_25_example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.itstep.android.lesson_25_example.R;
import org.itstep.android.lesson_25_example.model.Note;

/*
 * DetailsFragment.java
 *
 * Created by mib on 16.01.15, 20:18
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class DetailsFragment extends Fragment {

	public static final String EXTRA_KEY_NOTE = "EXTRA_KEY_NOTE";
	private Note mNote;

	public static DetailsFragment newInstance(Note note) {
		final Bundle args = new Bundle();
		args.putSerializable(EXTRA_KEY_NOTE, note);

		DetailsFragment fragment = new DetailsFragment();
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mNote = (Note) getArguments().getSerializable(EXTRA_KEY_NOTE);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_details, container, false);

		final TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
		titleTextView.setText(mNote.getTitle());

		return view;
	}
}