package org.itstep.android.lesson_38;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/*
 * RecyclerAdapter.java
 *
 * Created by MiB on 06.03.2015, 16:52
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

	private final LayoutInflater mLayoutInflater;
	private final List<MyModel> mItems = new ArrayList<>();

	public RecyclerAdapter(final Context context) {
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
		final View view = mLayoutInflater.inflate(R.layout.list_item_my_model, parent, false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		holder.bind(mItems.get(position));
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}

	public void addItem(final MyModel myModel) {
		mItems.add(myModel);

		final int position = mItems.size();
		notifyItemInserted(position);
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder {

		private final ImageView mImageView;
		private final TextView mTextView;

		public MyViewHolder(final View itemView) {
			super(itemView);

			mImageView = (ImageView) itemView.findViewById(R.id.image_view);
			mTextView = (TextView) itemView.findViewById(R.id.text_view);
		}

		public void bind(final MyModel myModel) {
			Picasso.with(mImageView.getContext())
					.load(myModel.getUrl())
					.into(mImageView);

			mTextView.setText(myModel.getName());
		}
	}
}