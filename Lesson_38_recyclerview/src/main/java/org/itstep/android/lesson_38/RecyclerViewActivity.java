package org.itstep.android.lesson_38;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

/*
 * RecyclerViewActivity.java
 *
 * Created by MiB on 06.03.2015, 16:39
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class RecyclerViewActivity extends ActionBarActivity
        implements View.OnClickListener {

    private static final String TAG = RecyclerViewActivity.class.getSimpleName();

    private static final String PHOTO_URL = "http://www.emoticonswallpapers.com/avatar/penguins/penguin%20pingouin%20pinguino%20783.png";
    private static final String EXPHOTO_URL = "http://upload.wikimedia.org/wikipedia/commons/c/c3/Aurora_as_seen_by_IMAGE.PNG";

    private RecyclerView mRecyclerView;
    private PhotoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, RecyclerViewActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mAdapter = new PhotoAdapter(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final Random rnd = new Random();

        final List<IPhoto> photoList = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            final String name = String.format("Item %d", i);

            final IPhoto photo;
            if (rnd.nextBoolean()) {
                photo = new Photo(name, PHOTO_URL);
            } else {
                final String description = String.format("Description %d", i);
                photo = new ExPhoto(name, description, EXPHOTO_URL);
            }

            photoList.add(photo);
        }

        mAdapter.addAll(photoList);

        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.remove_button).setOnClickListener(this);
        findViewById(R.id.insert_button).setOnClickListener(this);
        findViewById(R.id.orientation_button).setOnClickListener(this);
        findViewById(R.id.layout_button).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.add_button:
                addPhoto();
                break;
            case R.id.remove_button:
                removePhoto();
                break;
            case R.id.insert_button:
                insertPhoto(1);
            case R.id.orientation_button:
                changeOrientation();
                break;
            case R.id.layout_button:
                changeLayout();
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
                break;
        }
    }

    private void changeLayout() {
        if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            mLayoutManager = new LinearLayoutManager(this);
        } else {
            mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void changeOrientation() {
        if (mLayoutManager instanceof LinearLayoutManager) {
            final int orientation;
            final LinearLayoutManager layoutManager = (LinearLayoutManager) mLayoutManager;
            if (layoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
                orientation = StaggeredGridLayoutManager.HORIZONTAL;
            } else {
                orientation = StaggeredGridLayoutManager.VERTICAL;
            }
            layoutManager.setOrientation(orientation);
        }
    }

    private void insertPhoto(final int position) {
        final Random rnd = new Random();
        final StringBuilder text = new StringBuilder();

        for (int i = 0; i < rnd.nextInt(10); i++) {
            text.append("Text\n");
        }

        final Photo photo = new Photo(text.toString(), PHOTO_URL);
        mAdapter.addPhoto(photo, position);
    }

    private void removePhoto() {
        mAdapter.remove(0);
    }

    private void addPhoto() {
        final Random rnd = new Random();
        final StringBuilder text = new StringBuilder();

        for (int i = 0; i < rnd.nextInt(10); i++) {
            text.append("Text\n");
        }

        final Photo photo = new Photo(
                text.toString(),
                PHOTO_URL);
        mAdapter.addPhoto(photo, mAdapter.getItemCount());
    }
}