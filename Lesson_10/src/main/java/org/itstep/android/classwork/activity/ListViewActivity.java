package org.itstep.android.classwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.itstep.android.classwork.R;

import java.util.ArrayList;

public class ListViewActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

	private static final String TAG = ListViewActivity.class.getSimpleName();

	private static final int ITEMS_COUNT = 100;

	private ArrayList<String> mStrings;
	private ArrayAdapter<String> mAdapter;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ListViewActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		mStrings = new ArrayList<String>();

		mAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, mStrings);

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(this);

		final TextView emptyTextView = (TextView) findViewById(R.id.emptyTextView);
		listView.setEmptyView(emptyTextView);

		final Button fillButton = (Button) findViewById(R.id.fillButton);
		fillButton.setOnClickListener(this);

		// TODO [aivanchenko | 05.11.2014 16:42] Fill adapter
	}

	@Override
	public void onItemClick(final AdapterView<?> parent, final View view,
							final int position, final long id) {
		final Object itemAtPosition = parent.getItemAtPosition(position);
		if (itemAtPosition instanceof String) {
			final String item = (String) itemAtPosition;

			Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.fillButton:
				fillList();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void fillList() {
		for (int i = 0; i < ITEMS_COUNT; i++) {
			mStrings.add(String.format("Item %d", i));
		}
		mAdapter.notifyDataSetChanged();
	}
}
