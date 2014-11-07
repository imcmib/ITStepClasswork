package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Gallery;

import org.itstep.android.classwork.adapter.ImagesAdapter;

public class GalleryActivity extends Activity {

	private static final String TAG = GalleryActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, GalleryActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);

		final Gallery gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new ImagesAdapter(this));
	}
}
