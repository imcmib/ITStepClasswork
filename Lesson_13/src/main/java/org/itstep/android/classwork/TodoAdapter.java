package org.itstep.android.classwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/*
 * TodoAdaoter.java
 *
 * Created by mib on 19.11.14, 20:34
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TodoAdapter extends ArrayAdapter<Note> {

	private final LayoutInflater mLayoutInflater;
	private final int mLayoutResId;

	public TodoAdapter(final Context context, final int resource, final List<Note> objects) {
		super(context, resource, objects);

		mLayoutInflater = LayoutInflater.from(context);
		mLayoutResId = resource;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		final ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mLayoutInflater.inflate(mLayoutResId, null);

			viewHolder = new ViewHolder();
			viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
			viewHolder.descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final Note note = getItem(position);
		if (note == null) {
			return convertView;
		}

		viewHolder.nameTextView.setText(note.getName());
		viewHolder.descriptionTextView.setText(note.getDescription());

		return convertView;
	}

	private static class ViewHolder {
		TextView nameTextView;
		TextView descriptionTextView;
	}
}
