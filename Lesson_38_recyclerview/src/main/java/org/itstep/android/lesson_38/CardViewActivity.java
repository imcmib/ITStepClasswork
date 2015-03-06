package org.itstep.android.lesson_38;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/*
 * RecyclerViewActivity.java
 *
 * Created by MiB on 06.03.2015, 16:39
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class CardViewActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = CardViewActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private CardViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, CardViewActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mAdapter = new CardViewAdapter(this);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SpacingItemDecorator());
//        final SlideInLeftAnimator animator = new SlideInLeftAnimator();
//        animator.setAddDuration(2000);
//        animator.setRemoveDuration(2000);
//        mRecyclerView.setItemAnimator(animator);

        final List<String> strings = new ArrayList<>(10);

        for (int i = 0; i < 50; i++) {
            final String name = String.format("Item %d", i);
            strings.add(name);
        }

        mAdapter.addAll(strings);

        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.remove_button).setOnClickListener(this);
        findViewById(R.id.insert_button).setOnClickListener(this);
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
                break;
            case R.id.layout_button:
                mAdapter.move();
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
                break;
        }
    }

    private void insertPhoto(final int position) {
        mAdapter.add("Item", position);
    }

    private void removePhoto() {
        mAdapter.remove(0);
    }

    private void addPhoto() {
        mAdapter.add("Item", mAdapter.getItemCount());
    }
}