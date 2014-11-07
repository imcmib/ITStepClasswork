package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends Activity {

	private static final String TAG = GridViewActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, GridViewActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view);

		final List<String> data = generateStringList(100);

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, R.layout.simple_grid_item, android.R.id.text1, data);

		final GridView gridView = (GridView) findViewById(R.id.gridView);
		gridView.setAdapter(adapter);

		gridView.setNumColumns(data.size());
//		gridView.setNumColumns(GridView.AUTO_FIT);
//		final int width = (int) Utils.convertPixelsToDp(this, 120);
//		gridView.setColumnWidth(width);
//		gridView.setVerticalSpacing(5);
//		gridView.setHorizontalSpacing(5);
//		gridView.setStretchMode(GridView.NO_STRETCH);
	}

	private List<String> generateStringList(final int count) {
		final List<String> result = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			result.add(String.format("Item %d", i));
		}

		return result;
	}
}
