package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArrayAdapterActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ArrayAdapterActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_adapter);

		final String[] strings = getResources().getStringArray(R.array.texts);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, strings);

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}
}