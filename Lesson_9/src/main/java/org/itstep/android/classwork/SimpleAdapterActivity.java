package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleAdapterActivity extends Activity {

	// имена атрибутов для Map
	private static final String ATTRIBUTE_NAME_TEXT = "text";

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SimpleAdapterActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_adapter);

		// массивы данных
		final String[] texts = getResources().getStringArray(R.array.texts);

		// упаковываем данные в понятную для адаптера структуру
		final ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(texts.length);

		Map<String, Object> m;
		for (int i = 0; i < texts.length; i++) {
			m = new HashMap<String, Object>();
			m.put(ATTRIBUTE_NAME_TEXT, texts[i]);
			data.add(m);
		}

		// массив имен атрибутов, из которых будут читаться данные
		final String[] from = {
				ATTRIBUTE_NAME_TEXT
		};

		// массив ID View-компонентов, в которые будут вставлять данные
		final int[] to = {
				R.id.textView
		};

		// создаем адаптер
		final SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item, from, to);

		// определяем список и присваиваем ему адаптер
		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}
}