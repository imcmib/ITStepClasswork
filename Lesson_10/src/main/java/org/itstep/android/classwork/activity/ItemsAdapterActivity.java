package org.itstep.android.classwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.itstep.android.classwork.R;
import org.itstep.android.classwork.adapter.ItemsAdapter;
import org.itstep.android.classwork.model.Item;

import java.util.ArrayList;

public class ItemsAdapterActivity extends Activity implements AdapterView.OnItemClickListener {

	private static final int ITEMS_COUNT = 100;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ItemsAdapterActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		final ArrayList<Item> items = new ArrayList<Item>(ITEMS_COUNT);

		Item item;
		for (int i = 0; i < ITEMS_COUNT; i++) {
			final String name = String.format("Item %d", i);
			item = new Item(i, name);
			items.add(item);
		}

		final ItemsAdapter adapter = new ItemsAdapter(this, R.layout.list_item_item, items);

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(final AdapterView<?> parent, final View view,
							final int position, final long id) {
		Toast.makeText(this, String.format("Item id: %d", id), Toast.LENGTH_SHORT).show();
	}
}
