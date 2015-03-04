package org.itstep.android.lesson_37;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/*
 * RefreshActivity.java
 *
 * Created by mib on 04.03.15, 20:03
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class RefreshActivity extends ActionBarActivity
        implements SwipeRefreshLayout.OnRefreshListener {

    private ArrayAdapter<String> mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, RefreshActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        final List<String> items = getStrings(0);
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                items);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(mAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private List<String> getStrings(int offset) {
        final List<String> items = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            items.add(String.format("Item %d", offset + i));
        }
        return items;
    }

    @Override
    public void onRefresh() {
        final List<String> strings = getStrings(100);

        mAdapter.clear();
        mAdapter.addAll(strings);

        mSwipeRefreshLayout.setRefreshing(false);
    }
}