package org.itstep.android.classwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.itstep.android.classwork.R;
import org.itstep.android.classwork.adapter.ViewHolderAdapter;
import org.itstep.android.classwork.model.Item;

import java.util.ArrayList;

public class ViewHolderAdapterActivity extends Activity {

	private static final int ITEMS_COUNT = 100;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ViewHolderAdapterActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		final ArrayList<Item> items = new ArrayList<Item>(ITEMS_COUNT);

		Item item;
		for (int i = 0; i < ITEMS_COUNT; i++) {
			item = new Item(i, String.format("Item %d", i));
			items.add(item);
		}

		final ViewHolderAdapter adapter = new ViewHolderAdapter(this, R.layout.list_item_motherboard, items);

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);

		final TextView emptyTextView = (TextView) findViewById(R.id.emptyTextView);
		listView.setEmptyView(emptyTextView);
	}
}