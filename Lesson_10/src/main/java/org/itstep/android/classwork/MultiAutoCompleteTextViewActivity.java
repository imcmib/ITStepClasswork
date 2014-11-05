package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

public class MultiAutoCompleteTextViewActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, MultiAutoCompleteTextViewActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_autocomplete_text_tiew);

		final String[] words = new String[] {
				"Anderson, Jacob", "Duncan, Emily", "Fuller, Michael",
				"Cotman, Matthew", "Lawson, 01ivia", "Chapman, Andrew",
				"Godwin, Joseph", "Bush, Samantha", "Gateman, Christopher"
		};

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, words);

		final MultiAutoCompleteTextView multiAutoCompleteTextView =
				(MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);

		multiAutoCompleteTextView.setAdapter(adapter);
		multiAutoCompleteTextView.setThreshold(1);
		multiAutoCompleteTextView.setTokenizer(
				new MultiAutoCompleteTextView.CommaTokenizer());
	}
}
