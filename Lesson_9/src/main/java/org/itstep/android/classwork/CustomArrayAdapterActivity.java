package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class CustomArrayAdapterActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, CustomArrayAdapterActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_adapter);

		final Random rnd = new Random();

		final ArrayList<Human> humans = new ArrayList<Human>();
		Human human;
		for (int i = 0; i < 50; i++) {
			human = new Human(
					String.format("Human %d", i),
					rnd.nextInt(100),
					R.drawable.ic_launcher
			);
			humans.add(human);
		}

		final MyAdapter adapter = new MyAdapter(this,
				R.layout.list_item_array_adapter, humans);

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}
}