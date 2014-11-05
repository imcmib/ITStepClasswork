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
 * ItemsAdapter.java
 *
 * Created by aivanchenko on 05.11.2014, 16:25
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ItemsAdapter extends ArrayAdapter<Item> {

	private final LayoutInflater mLayoutInflater;
	private final int mLayoutResId;

	public ItemsAdapter(final Context context, final int resource, final List<Item> objects) {
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
	public long getItemId(final int position) {
		return getItem(position).getId();
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
}