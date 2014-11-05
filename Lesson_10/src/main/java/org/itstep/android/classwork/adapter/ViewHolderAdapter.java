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
public class ViewHolderAdapter extends ArrayAdapter<Item> {

	private final LayoutInflater mLayoutInflater;
	private final int mLayoutResId;

	public ViewHolderAdapter(final Context context, final int resource, final List<Item> objects) {
		super(context, resource, objects);

		mLayoutInflater = LayoutInflater.from(context);
		mLayoutResId = resource;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		final ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mLayoutInflater.inflate(mLayoutResId, parent, false);

			viewHolder = new ViewHolder();
			viewHolder.idTextView = (TextView) convertView.findViewById(R.id.idTextView);
			viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}


		final Item item = getItem(position);

		viewHolder.idTextView.setText(String.valueOf(item.getId()));
		viewHolder.nameTextView.setText(item.getName());

		return convertView;
	}

	private static class ViewHolder {
		public TextView idTextView;
		public TextView nameTextView;
	}
}