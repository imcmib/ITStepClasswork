package ort.itstep.android.lesson_27_shared_prefs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/*
 * PrefsActivity.java
 *
 * Created by aivanchenko on 23.01.2015, 16:55
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class PrefsActivity extends PreferenceActivity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, PrefsActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.prefs);
	}
}