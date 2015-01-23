package ort.itstep.android.lesson_27_shared_prefs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;

/*
 * ReadPrefsActivity.java
 *
 * Created by aivanchenko on 23.01.2015, 17:00
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ReadPrefsActivity extends ActionBarActivity {

	private CheckBox mCheckBox;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ReadPrefsActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_prefs);

		findViewById(R.id.settingsButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				PrefsActivity.startActivity(ReadPrefsActivity.this);
			}
		});

		mCheckBox = (CheckBox) findViewById(R.id.checkBox);
	}

	@Override
	protected void onResume() {
		super.onResume();

		final SharedPreferences preferences =
				PreferenceManager.getDefaultSharedPreferences(this);

		final boolean checked = preferences.getBoolean("checkBox", false);
		final String text = preferences.getString("editText", "");

		mCheckBox.setChecked(checked);
		mCheckBox.setText(text);
	}
}