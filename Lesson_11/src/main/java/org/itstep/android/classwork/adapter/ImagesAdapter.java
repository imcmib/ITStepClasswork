package org.itstep.android.classwork.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import org.itstep.android.classwork.R;

import java.util.Random;

/*
 * ImagesAdapter.java
 *
 * Created by aivanchenko on 07.11.2014, 17:12
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ImagesAdapter extends BaseAdapter {

	private final Context mContext;
	private final Random mRnd;

	private final Integer[] mImage = {
			R.drawable.android1, R.drawable.android2,
			R.drawable.android3, R.drawable.android4,
			R.drawable.android5, R.drawable.android6,
			R.drawable.android7, R.drawable.android8,
			R.drawable.android9
	};

	public ImagesAdapter(Context сontext) {
		mContext = сontext;
		mRnd = new Random();
	}

	@Override
	public int getCount() {
		return mImage.length;
	}

	@Override
	public Object getItem(int position) {
		return mImage[position];
	}

	@Override
	public long getItemId(int position) {
		return mImage[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ImageView imageView = new ImageView(mContext);
		imageView.setLayoutParams(new Gallery.LayoutParams(250, 250));
		imageView.setPadding(20, 20, 20, 20);
		imageView.setImageResource(mImage[position]);
		imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView.setBackgroundColor(Color.argb(255, mRnd.nextInt(255), mRnd.nextInt(255), mRnd.nextInt(255)));

		return imageView;
	}
}