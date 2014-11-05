package org.itstep.android.classwork.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.itstep.android.classwork.R;

public class AutoCompleteTextViewActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, AutoCompleteTextViewActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autocomplete_text_tiew);

		final String[] words = new String[] {
			"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
		};

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, words);

		final AutoCompleteTextView autoCompleteTextView =
				(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

		autoCompleteTextView.setAdapter(adapter);
		autoCompleteTextView.setThreshold(1);
		autoCompleteTextView.setCompletionHint("Hint");
	}
}
