package org.itstep.android.lesson_38;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/*
 * RecyclerViewActivity.java
 *
 * Created by MiB on 06.03.2015, 16:39
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class RecyclerViewActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = RecyclerViewActivity.class.getSimpleName();

	private RecyclerView mRecyclerView;
	private RecyclerAdapter mAdapter;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, RecyclerViewActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view);

		mAdapter = new RecyclerAdapter(this);

		mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		mRecyclerView.setAdapter(mAdapter);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		findViewById(R.id.add_button).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.add_button:
				final MyModel myModel = new MyModel(
						"My object 1",
						"http://www.emoticonswallpapers.com/avatar/penguins/penguin%20pingouin%20pinguino%20783.png");
				mAdapter.addItem(myModel);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}