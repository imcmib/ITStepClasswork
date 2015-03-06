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
import java.util.Collection;
import java.util.List;

/*
 * CardViewAdapter.java
 *
 * Created by mib on 06.03.15, 20:01
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<String> mList = new ArrayList<>();

    public CardViewAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CardViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = mLayoutInflater.inflate(R.layout.list_item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        holder.update(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(final Collection<String> items) {
        mList.addAll(items);

        notifyDataSetChanged();
    }

    public void add(final String item, final int position) {
        mList.add(position, item);

        notifyItemInserted(position);
    }

    public void remove(final int i) {
        mList.remove(i);

        notifyItemRemoved(i);
    }

    public void move() {
//        final String first = mList.get(2);
//        mList.remove(2);
//
//        mList.add(4, first);

        notifyItemMoved(0, 49);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        private static final String EXPHOTO_URL = "http://upload.wikimedia.org/wikipedia/commons/c/c3/Aurora_as_seen_by_IMAGE.PNG";

        private final ImageView mImageView;
        private final TextView mTextView;

        public CardViewHolder(final View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.image_view);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
        }

        public void update(final String text) {
//            Picasso.with(mImageView.getContext())
//                    .load(EXPHOTO_URL)
//                    .into(mImageView);

            mTextView.setText(text);
        }
    }
}