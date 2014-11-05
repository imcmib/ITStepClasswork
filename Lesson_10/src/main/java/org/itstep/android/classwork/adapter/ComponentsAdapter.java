package org.itstep.android.classwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.itstep.android.classwork.R;
import org.itstep.android.classwork.model.Item;
import org.itstep.android.classwork.model.MotherBoard;
import org.itstep.android.classwork.model.VideoCard;

import java.util.List;

/*
 * ComponentsAdapter.java
 *
 * Created by aivanchenko on 05.11.2014, 16:46
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ComponentsAdapter extends ArrayAdapter<Item> {

	private static final int VIEW_TYPE_MOTHERBOARD = 0;
	private static final int VIEW_TYPE_VIDEOCARD = 1;
	private static final int VIEW_TYPE_COUNT = VIEW_TYPE_VIDEOCARD + 1;

	private final LayoutInflater mLayoutInflater;

	public ComponentsAdapter(final Context context, final int resource, final List<Item> objects) {
		super(context, resource, objects);

		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE_COUNT;
	}

	@Override
	public int getItemViewType(final int position) {
		final Item item = getItem(position);
		if (item instanceof MotherBoard) {
			return VIEW_TYPE_MOTHERBOARD;
		} else if (item instanceof VideoCard) {
			return VIEW_TYPE_VIDEOCARD;
		} else {
			return -1;
		}
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		switch (getItemViewType(position)) {
			case VIEW_TYPE_MOTHERBOARD:
				return getMotherBoardView(position);
			case VIEW_TYPE_VIDEOCARD:
				return getVideoCardView(position);
			default:
				return null;
		}
	}

	private View getMotherBoardView(final int position) {
		final View view = mLayoutInflater.inflate(R.layout.list_item_motherboard, null);

		final MotherBoard motherBoard = (MotherBoard) getItem(position);

		final TextView idTextView = (TextView) view.findViewById(R.id.idTextView);
		idTextView.setText(String.format("ID: %d", motherBoard.getId()));

		final TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
		nameTextView.setText(String.format("Name: %s", motherBoard.getName()));

		final TextView modelTextView = (TextView) view.findViewById(R.id.modelTextView);
		modelTextView.setText(motherBoard.getModel());

		return view;
	}

	private View getVideoCardView(final int position) {
		final View view = mLayoutInflater.inflate(R.layout.list_item_videocard, null);

		final VideoCard videoCard = (VideoCard) getItem(position);

		final TextView idTextView = (TextView) view.findViewById(R.id.idTextView);
		idTextView.setText(String.format("ID: %d", videoCard.getId()));

		final TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
		nameTextView.setText(String.format("Name: %s", videoCard.getName()));

		final TextView ramTextView = (TextView) view.findViewById(R.id.ramTextView);
		ramTextView.setText(String.format("RAM: %dMB", videoCard.getRam()));

		return view;
	}
}