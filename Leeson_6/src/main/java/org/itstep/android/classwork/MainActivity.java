package org.itstep.android.classwork;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

		TextView textView = (TextView) findViewById(R.id.textView);

		//		texViewLineSpacing(textView);

		//		textViewBackground(textView);

		//		textViewShadow(textView);

		//		textViewLinksWeb(textView);

		//		textViewLinksPhone(textView);

		final EditText editText = (EditText) findViewById(R.id.editText);

		//		editTextInputType(editText);

		//		editTextFilters(editText);

		//		editTextEms(editText);

		//		editTextHint(editText);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				final String string = editText.getText().toString();

				Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void editTextHint(final EditText editText) {
		editText.setHint(R.string.hello_world);
	}

	private void editTextEms(final EditText editText) {
		editText.setEms(13);
		editText.setMaxEms(13);
		editText.setMinEms(5);
	}

	private void editTextFilters(final EditText editText) {
		final InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(5);
		final InputFilter.AllCaps allCaps = new InputFilter.AllCaps();

		final InputFilter[] filters = new InputFilter[2];
		filters[0] = lengthFilter;
		filters[1] = allCaps;

		editText.setFilters(filters);
	}

	private void editTextInputType(final EditText editText) {
		editText.setInputType(InputType.TYPE_CLASS_DATETIME);
	}

	private void textViewLinksWeb(final TextView textView) {
		textView.setAutoLinkMask(Linkify.WEB_URLS);
		textView.setLinksClickable(false);
		textView.setText("Go: http://google.com hello");
	}

	private void textViewLinksPhone(final TextView textView) {
		textView.setAutoLinkMask(Linkify.PHONE_NUMBERS);
		textView.setLinksClickable(true);
		textView.setLinkTextColor(Color.RED);
		textView.setText("Call: +380671312312");
	}

	private void textViewShadow(final TextView textView) {
		textView.setText("Hello World!");
		textView.setTextColor(Color.WHITE);
		textView.setShadowLayer(2, 2, 2, Color.BLACK);
	}

	private void textViewBackground(final TextView textView) {
		textView.setText("Hello World!");
		textView.setBackgroundColor(getResources().getColor(R.color.red));
		textView.setBackgroundResource(R.color.red);
	}

	private void texViewLineSpacing(final TextView textView) {
		textView.setText("Hello World!\nHello World!\nHello World!");
		textView.setText(getString(R.string.label_hello_long));
		textView.setLineSpacing(0f, 2f);
	}
}
