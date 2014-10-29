package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

/*
 * ImageViewActivity.java
 *
 * Created by aivanchenko on 29.10.2014, 12:49
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class HorizontalScrollViewActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, HorizontalScrollViewActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_horizontal_scroll_view);

		final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

		final Random rnd = new Random();

		for (int i = 0; i < 100; i++) {
			final TextView textView = new TextView(this);
			textView.setText(String.format("TextView #%d", i));
			textView.setTextSize(rnd.nextInt(150) + 20);
			textView.setTextColor(Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));

			linearLayout.addView(textView);
		}
	}
}