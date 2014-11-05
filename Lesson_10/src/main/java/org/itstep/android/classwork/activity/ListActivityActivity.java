package org.itstep.android.classwork.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.itstep.android.classwork.R;

import java.util.ArrayList;

public class ListActivityActivity extends ListActivity {

	private static final int ITEMS_COUNT = 100;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ListActivityActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		final ArrayList<String> items = new ArrayList<String>(ITEMS_COUNT);
		for (int i = 0; i < ITEMS_COUNT; i++) {
			items.add(String.format("Item %d", i));
		}

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);

		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(final ListView parent, final View view, final int position, final long id) {
		final Object itemAtPosition = parent.getItemAtPosition(position);
		if (itemAtPosition instanceof String) {
			final String item = (String) itemAtPosition;

			Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
		}
	}
}