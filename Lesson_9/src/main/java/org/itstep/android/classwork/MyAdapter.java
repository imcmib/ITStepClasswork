package org.itstep.android.classwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
 * MyAdapter.java
 *
 * Created by mib on 31.10.14, 20:24
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyAdapter extends ArrayAdapter<Human> {

	private final LayoutInflater mInflater;

	public MyAdapter(final Context context, final int resource,
					 final List<Human> objects) {
		super(context, resource, objects);

		mInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(final int position, final View convertView,
						final ViewGroup parent) {

		final View view = mInflater.inflate(R.layout.list_item_array_adapter, null);

		final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
		final TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);

		final Human human = getItem(position);

		imageView.setImageResource(human.getPhoto());
		nameTextView.setText(String.format("%s (%d)", human.getName(), human.getAge()));

		return view;
	}
}
