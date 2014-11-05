package org.itstep.android.classwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.itstep.android.classwork.R;
import org.itstep.android.classwork.adapter.ComponentsAdapter;
import org.itstep.android.classwork.model.Item;
import org.itstep.android.classwork.model.MotherBoard;
import org.itstep.android.classwork.model.VideoCard;

import java.util.ArrayList;
import java.util.Random;

public class ComponentsAdapterActivity extends Activity {

	private static final int ITEMS_COUNT = 100;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ComponentsAdapterActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		final Random rnd = new Random();
		final ArrayList<Item> items = new ArrayList<Item>(ITEMS_COUNT);

		Item item;
		for (int i = 0; i < ITEMS_COUNT; i++) {
			final String name = String.format("Item %d", i);
			if (rnd.nextBoolean()) {
				final String model = String.format("Model %d", rnd.nextInt(100));
				item = new MotherBoard(i, name, model);
			} else {
				item = new VideoCard(i, name, rnd.nextInt(2048));
			}
			items.add(item);
		}

		final ComponentsAdapter adapter = new ComponentsAdapter(this, R.layout.list_item_item, items);

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);

		final TextView emptyTextView = (TextView) findViewById(R.id.emptyTextView);
		listView.setEmptyView(emptyTextView);
	}
}
