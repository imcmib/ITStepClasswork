package org.itstep.android.classwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.itstep.android.classwork.R;
import org.itstep.android.classwork.model.Item;

import java.util.List;

/*
 * SpinnerItemsAdapter.java
 *
 * Created by aivanchenko on 07.11.2014, 16:53
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SpinnerItemsAdapter extends ArrayAdapter<Item> {

	private final LayoutInflater mLayoutInflater;
	private final int mLayoutResId;
	private int mDropDownLayoutResId;

	public SpinnerItemsAdapter(final Context context, final int resource, final List<Item> objects) {
		super(context, resource, objects);

		mLayoutInflater = LayoutInflater.from(context);
		mLayoutResId = resource;
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		final View view = mLayoutInflater.inflate(mLayoutResId, null);

		final Item item = getItem(position);

		final TextView idTextView = (TextView) view.findViewById(R.id.idTextView);
		idTextView.setText(String.valueOf(item.getId()));

		final TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
		nameTextView.setText(item.getName());

		return view;
	}

	@Override
	public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
		final View view = mLayoutInflater.inflate(mDropDownLayoutResId, null);

		final Item item = getItem(position);

		final TextView textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(String.format("Item id: %d, name: %s", item.getId(), item.getName()));

		return view;
	}

	@Override
	public long getItemId(final int position) {
		return getItem(position).getId();
	}

	@Override
	public void setDropDownViewResource(final int resource) {
		super.setDropDownViewResource(resource);

		mDropDownLayoutResId = resource;
	}
}