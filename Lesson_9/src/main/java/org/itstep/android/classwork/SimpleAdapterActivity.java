package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SimpleAdapterActivity extends Activity {

	// имена атрибутов для Map
	private static final String ATTRIBUTE_TITLE = "title";
	private static final String ATTRIBUTE_DESCRIPTION = "description";
	private static final String ATTRIBUTE_IMAGE = "image";
	private static final String ATTRIBUTE_CHECK = "check";
	private static final String ATTRIBUTE_SEEK = "seek";

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SimpleAdapterActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_adapter);

		// массивы данных
		final String[] titles = getResources().getStringArray(R.array.texts);
		final String[] descriptions = new String[] {
				"Description 1",
				"Description 2",
				"Description 3",
				"Description 4",
				"Description 5",
				"Description 6",
				"Description 7",
				"Description 8",
		};
		final int imageResId = R.drawable.ic_launcher;

		// упаковываем данные в понятную для адаптера структуру
		final ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(titles.length);

		Random rnd = new Random();

		Map<String, Object> map;
		for (int i = 0; i < titles.length; i++) {
			map = new HashMap<String, Object>();
			map.put(ATTRIBUTE_TITLE, titles[i]);
			map.put(ATTRIBUTE_DESCRIPTION, descriptions[i]);
			map.put(ATTRIBUTE_IMAGE, imageResId);
			map.put(ATTRIBUTE_CHECK, rnd.nextBoolean());
			map.put(ATTRIBUTE_SEEK, rnd.nextInt(100));

			data.add(map);
		}

		// массив имен атрибутов, из которых будут читаться данные
		final String[] from = {
				ATTRIBUTE_TITLE,
				ATTRIBUTE_DESCRIPTION,
				ATTRIBUTE_IMAGE,
				ATTRIBUTE_CHECK,
				ATTRIBUTE_SEEK
		};

		// массив ID View-компонентов, в которые будут вставлять данные
		final int[] to = {
				R.id.titleTextView,
				R.id.descriptionTextView,
				R.id.imageView,
				R.id.checkBox,
				R.id.seekBar
		};

		// создаем адаптер
		final SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item, from, to);
		adapter.setViewBinder(new SeekBarViewBinder());

		// определяем список и присваиваем ему адаптер
		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}

	private static class SeekBarViewBinder implements SimpleAdapter.ViewBinder {
		@Override
		public boolean setViewValue(final View view, final Object data, final String textRepresentation) {
			if (view instanceof SeekBar) {
				((SeekBar) view).setProgress((Integer) data);
				return true;
			}
			return false;
		}
	}
}