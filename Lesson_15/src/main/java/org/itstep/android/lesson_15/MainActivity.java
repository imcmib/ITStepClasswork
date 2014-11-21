package org.itstep.android.lesson_15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	public static final String EXTRA_MY_KEY = "EXTRA_MY_KEY";
	public static final int REQUEST_CODE_SECOND_ACTIVITY = 100;

	private Note mNote;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNote = new Note("Some note");

		mButton = (Button) findViewById(R.id.button);
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtra(EXTRA_MY_KEY, mNote);
				startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
			}
		});
	}

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode,
									final Intent data) {
		switch (requestCode) {
			case REQUEST_CODE_SECOND_ACTIVITY:
				onSecondActivityResult(resultCode, data);
				break;
			default:
				super.onActivityResult(requestCode, resultCode, data);
				break;
		}
	}

	private void onSecondActivityResult(final int resultCode, final Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			final Note note = (Note) data.getSerializableExtra(EXTRA_MY_KEY);
			mButton.setText(note.getName());
		} else if (resultCode == Activity.RESULT_CANCELED) {
			mButton.setText("Cancelled");
		} else {
			mButton.setText("Unknown");
		}
	}
}