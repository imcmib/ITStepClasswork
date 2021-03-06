package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/*
 * ImageViewActivity.java
 *
 * Created by aivanchenko on 29.10.2014, 12:49
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ScrollViewActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ScrollViewActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll_view);

		final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

		for (int i = 0; i < 100; i++) {
			final TextView textView = new TextView(this);
			textView.setText(String.format("TextView #%d", i));
			textView.setId(i);

			linearLayout.addView(textView);
		}

		findViewById(R.id.scrollTo50Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				final View viewById = findViewById(50);
				final int y = (int) viewById.getY();

				ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
//				scrollView.scrollTo(0, y);
				scrollView.smoothScrollTo(0, y);
			}
		});

		findViewById(R.id.scrollBy500Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
//				scrollView.scrollBy(0, 500);
				scrollView.smoothScrollBy(0, 500);
			}
		});
	}
}