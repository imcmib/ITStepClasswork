package org.itstep.android.lesson_30_network;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.fileDownloadButton).setOnClickListener(this);
		findViewById(R.id.htmlButton).setOnClickListener(this);
		findViewById(R.id.retrofitButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
			case R.id.fileDownloadButton:
				FileDownloadActivity.startActivity(this);
				break;
			case R.id.htmlButton:
				HtmlActivity.startActivity(this);
				break;
			case R.id.retrofitButton:
				RetrofitActivity.startActivity(this);
				break;
		}
	}
}