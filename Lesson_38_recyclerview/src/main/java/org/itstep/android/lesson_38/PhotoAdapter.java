package org.itstep.android.lesson_38;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/*
 * RecyclerAdapter.java
 *
 * Created by MiB on 06.03.2015, 16:52
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<IPhoto> mPhotos = new ArrayList<>();

    private static final int VIEW_TYPE_PHOTO = 0;
    private static final int VIEW_TYPE_EXPHOTO = 1;

    public PhotoAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(final int position) {
        final IPhoto photo = mPhotos.get(position);
        if (photo instanceof Photo) {
            return VIEW_TYPE_PHOTO;
        } else {
            return VIEW_TYPE_EXPHOTO;
        }
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        switch (viewType) {
            case VIEW_TYPE_PHOTO:
            default:
                final View photoView =
                        mLayoutInflater.inflate(R.layout.list_item_photo, parent, false);
                return new PhotoViewHolder(photoView);
            case VIEW_TYPE_EXPHOTO:
                final View exPhotoView =
                        mLayoutInflater.inflate(R.layout.list_item_exphoto, parent, false);
                return new ExPhotoViewHolder(exPhotoView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case VIEW_TYPE_PHOTO:
                ((PhotoViewHolder) holder).update(position, mPhotos.get(position));
                break;
            case VIEW_TYPE_EXPHOTO:
                ((ExPhotoViewHolder) holder).update(position, mPhotos.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public void addPhoto(final IPhoto photo, final int position) {
        mPhotos.add(photo);

        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (mPhotos.isEmpty() || position >= mPhotos.size()) {
            return;
        }

        mPhotos.remove(position);

        notifyItemRemoved(position);
    }

    public void addAll(final Collection<IPhoto> photoList) {
        final int startPosition = mPhotos.size();

        mPhotos.addAll(photoList);

        notifyItemRangeInserted(startPosition, photoList.size());
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;
        private final TextView mTextView;

        public PhotoViewHolder(final View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.image_view);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);

            final Random rnd = new Random();
            itemView.setBackgroundColor(
                    Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        }

        public void update(final int position, final IPhoto photo) {
            Picasso.with(mImageView.getContext())
                   .load(photo.getUrl())
                   .into(mImageView);

            mTextView.setText(photo.getName());

            if (position == 3
                    && itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                final StaggeredGridLayoutManager.LayoutParams layoutParams =
                        (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
                layoutParams.setFullSpan(true);
            }
        }
    }

    public static class ExPhotoViewHolder extends PhotoViewHolder {

        private final TextView mDescriptionTextView;

        public ExPhotoViewHolder(final View itemView) {
            super(itemView);

            mDescriptionTextView = (TextView) itemView.findViewById(R.id.description_text_view);

            final Random rnd = new Random();
            itemView.setBackgroundColor(
                    Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        }

        public void update(final int position, final IPhoto photo) {
            super.update(position, photo);

            final ExPhoto exPhoto = (ExPhoto) photo;
            mDescriptionTextView.setText(exPhoto.getDescription());
        }
    }
}