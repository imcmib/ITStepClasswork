package org.itstep.android.classwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * MyAdapter.java
 *
 * Created by mib on 14.11.14, 20:18
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyAdapter extends ArrayAdapter<String> {

	private final LayoutInflater mLayoutInflater;

	public MyAdapter(final Context context, final int resource, final String[] objects) {
		super(context, resource, objects);

		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		final View view = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, null);

		TextView textView = (TextView) view.findViewById(android.R.id.text1);
		textView.setText(getItem(position));

		return view;
	}
}