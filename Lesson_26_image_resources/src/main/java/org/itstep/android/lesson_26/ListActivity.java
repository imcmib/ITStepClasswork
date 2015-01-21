package org.itstep.android.lesson_26;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/*
 * ListActivity.java
 *
 * Created by mib on 21.01.15, 20:32
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

	private ListView mListView;
	private ArrayAdapter<String> mAdapter;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ListActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		final List<String> items = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			items.add(String.format("Item %d", i));
		}
		mAdapter = new ArrayAdapter<>(this, R.layout.list_item, items);

		mListView = (ListView) findViewById(R.id.listView);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	@Override
	public void onItemClick(final AdapterView<?> parent, final View view,
							final int position, final long id) {
		mListView.setItemChecked(position, true);
	}
}