package org.itstep.android.classwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.itstep.android.classwork.R;
import org.itstep.android.classwork.model.GalleryImage;

import java.util.List;

/*
 * GalleryAdapter.java
 *
 * Created by mib on 07.11.14, 20:08
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class GalleryAdapter extends ArrayAdapter<GalleryImage> {

	private final LayoutInflater mLayoutInflater;
	private final int mLayoutResId;

	public GalleryAdapter(final Context context, final int resource, final List<GalleryImage> objects) {
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
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
			viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
			viewHolder.descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final GalleryImage galleryImage = getItem(position);

		viewHolder.imageView.setImageResource(galleryImage.getImageResId());
		viewHolder.nameTextView.setText(galleryImage.getName());
		viewHolder.descriptionTextView.setText(galleryImage.getDescription());

		return convertView;
	}

	private static class ViewHolder {
		ImageView imageView;
		TextView nameTextView;
		TextView descriptionTextView;
	}
}
