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
public class ScrollViewWithHorizontalActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ScrollViewWithHorizontalActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll_view_with_horizontal);


		final StringBuilder text = new StringBuilder();

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 20; j++) {
				text.append("Text ");
			}
			text.append("\n");
		}

		TextView textView = (TextView) findViewById(R.id.textView);
		textView.setText(text);
		textView.setTextSize(20);
	}
}