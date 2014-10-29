package org.itstep.android.classwork;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.imageViewButton).setOnClickListener(this);
		findViewById(R.id.analogClockButton).setOnClickListener(this);
		findViewById(R.id.digitalClockViewButton).setOnClickListener(this);
		findViewById(R.id.textClockViewButton).setOnClickListener(this);
		findViewById(R.id.chronometerViewButton).setOnClickListener(this);
		findViewById(R.id.scrollViewViewButton).setOnClickListener(this);
		findViewById(R.id.horizontalScrollViewViewButton).setOnClickListener(this);
		findViewById(R.id.scrollViewWithHorizontalViewButton).setOnClickListener(this);
		findViewById(R.id.switchButton).setOnClickListener(this);
		findViewById(R.id.webViewButton).setOnClickListener(this);
		findViewById(R.id.datePickerButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.imageViewButton:
				ImageViewActivity.startActivity(this);
				break;
			case R.id.analogClockButton:
				AnalogClockActivity.startActivity(this);
				break;
			case R.id.digitalClockViewButton:
				DigitalClockActivity.startActivity(this);
				break;
			case R.id.textClockViewButton:
				TextClockActivity.startActivity(this);
				break;
			case R.id.chronometerViewButton:
				ChronometerActivity.startActivity(this);
				break;
			case R.id.scrollViewViewButton:
				ScrollViewActivity.startActivity(this);
				break;
			case R.id.horizontalScrollViewViewButton:
				HorizontalScrollViewActivity.startActivity(this);
				break;
			case R.id.scrollViewWithHorizontalViewButton:
				ScrollViewWithHorizontalActivity.startActivity(this);
				break;
			case R.id.switchButton:
				SwitchActivity.startActivity(this);
				break;
			case R.id.webViewButton:
				WebViewActivity.startActivity(this);
				break;
			case R.id.datePickerButton:
				DatePickerActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}