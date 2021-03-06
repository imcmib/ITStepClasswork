package ort.itstep.android.lesson_27_shared_prefs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * SaveLoadActivity.java
 *
 * Created by aivanchenko on 23.01.2015, 16:39
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SaveLoadActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = SaveLoadActivity.class.getSimpleName();

	public static final String KEY_TEXT = "KEY_TEXT";

	private EditText mEditText;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SaveLoadActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_load);

		mEditText = (EditText) findViewById(R.id.editText);

		findViewById(R.id.saveButton).setOnClickListener(this);
		findViewById(R.id.loadButton).setOnClickListener(this);
		findViewById(R.id.removeButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.saveButton:
				saveData();
				break;
			case R.id.loadButton:
				loadData();
				break;
			case R.id.removeButton:
				removeData();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view: " + getResources().getResourceName(view.getId()));
				break;
		}
	}

	private void saveData() {
		final String text = mEditText.getText().toString();

		final SharedPreferences preferences = getPreferences(MODE_PRIVATE);

		final SharedPreferences.Editor editor = preferences.edit();
		editor.putString(KEY_TEXT, text);
		editor.putLong("key_long", 1);
		editor.putBoolean("key_bool", true);
		editor.putFloat("key_float", 0.44f);
		editor.putInt("key_int", 222);
		final HashSet<String> values = new HashSet<>();
		values.add("item 1");
		values.add("item 2");
		values.add("item 3");
		editor.putStringSet("key_set", values);
		editor.commit();

		mEditText.getText().clear();
	}

	private void loadData() {
		final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		final String text = preferences.getString(KEY_TEXT, "");

		final Map<String, ?> all = preferences.getAll();
		for (Map.Entry<String, ?> entry : all.entrySet()) {
			Log.v(TAG, String.format("%s = %s", entry.getKey(), entry.getValue()));
		}

		mEditText.setText(text);
	}

	private void removeData() {
		final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		final SharedPreferences.Editor editor = preferences.edit();
		editor.remove(KEY_TEXT);
		editor.commit();
	}
}