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

		findViewById(R.id.spinnerButton).setOnClickListener(this);
		findViewById(R.id.galleryButton).setOnClickListener(this);
		findViewById(R.id.gridViewButton).setOnClickListener(this);
		findViewById(R.id.customGalleryButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.spinnerButton:
				SpinnerActivity.startActivity(this);
				break;
			case R.id.galleryButton:
				GalleryActivity.startActivity(this);
				break;
			case R.id.gridViewButton:
				GridViewActivity.startActivity(this);
				break;
			case R.id.customGalleryButton:
				CustomGalleryActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}
