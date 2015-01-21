package org.itstep.android.lesson_26;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class NinePatchActivity extends ActionBarActivity {

	private boolean mNinePatch;

	private TextView mTextView;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, NinePatchActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nine_patch);

		mTextView = (TextView) findViewById(R.id.textView);

		findViewById(R.id.toggleButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				mNinePatch = !mNinePatch;

				updateUI();
			}
		});

		updateUI();
	}

	private void updateUI() {
		mTextView.setBackgroundResource(mNinePatch ? R.drawable.example_nine : R.drawable.example);
	}
}