package org.itstep.android.lesson_25_example.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.itstep.android.lesson_25_example.R;
import org.itstep.android.lesson_25_example.model.Note;

import java.util.ArrayList;
import java.util.List;

/*
 * MesterFragment1.java
 *
 * Created by mib on 16.01.15, 19:56
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MasterFragment extends ListFragment {

	private MasterCallback mCallback;
	private ArrayAdapter<Note> mAdapter;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final List<Note> notes = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			notes.add(new Note(i, String.format("Note %d", i)));
		}

		mAdapter = new ArrayAdapter<>(
				getActivity(),
				R.layout.list_item_note,
				R.id.titleTextView,
				notes);

		setListAdapter(mAdapter);
	}

	@Override
	public void onAttach(final Activity activity) {
		super.onAttach(activity);

		if (activity instanceof MasterCallback) {
			mCallback = (MasterCallback) activity;
		} else {
			mCallback = null;
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();

		mCallback = null;
	}

	@Override
	public void onListItemClick(final ListView l, final View v, final int position, final long id) {
		if (mCallback != null) {
			final Note note = mAdapter.getItem(position);
			mCallback.onMasterListClick(note);
		}
	}

	public static interface MasterCallback {

		public void onMasterListClick(Note note);
	}
}