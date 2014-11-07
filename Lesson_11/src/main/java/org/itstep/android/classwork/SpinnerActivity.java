package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.itstep.android.classwork.adapter.SpinnerItemsAdapter;
import org.itstep.android.classwork.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends Activity {

	private static final String TAG = SpinnerActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SpinnerActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);

		final List<String> data = generateStringList(20);

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		final Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(adapter);

//		spinner.setPrompt("Title");

//		spinner.setSelection(2);

		final List<Item> items = generateItemsList(30);

		final SpinnerItemsAdapter spinnerItemsAdapter = new SpinnerItemsAdapter(this, R.layout.spinner_item, items);
		spinnerItemsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

		final Spinner itemsSpinner = (Spinner) findViewById(R.id.itemsSpinner);
		itemsSpinner.setAdapter(spinnerItemsAdapter);
		itemsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
				Toast.makeText(SpinnerActivity.this, String.format("Item id: " + id), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(final AdapterView<?> parent) {
				Toast.makeText(SpinnerActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
			}
		});


		itemsSpinner.setVisibility(View.GONE);

		findViewById(R.id.setPositionButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				spinner.setSelection(5);
			}
		});

		findViewById(R.id.getPositionButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				final String selectedItem = (String) spinner.getSelectedItem();
				Toast.makeText(SpinnerActivity.this, selectedItem, Toast.LENGTH_LONG).show();
			}
		});
	}

	private List<String> generateStringList(final int count) {
		final List<String> result = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			result.add(String.format("Item %d", i));
		}

		return result;
	}

	private List<Item> generateItemsList(final int count) {
		final List<Item> result = new ArrayList<Item>();

		for (int i = 0; i < count; i++) {
			result.add(new Item(i, String.format("Item %d", i)));
		}

		return result;
	}
}