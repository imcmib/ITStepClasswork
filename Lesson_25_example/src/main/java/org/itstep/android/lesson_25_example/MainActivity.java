package org.itstep.android.lesson_25_example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import org.itstep.android.lesson_25_example.fragment.DetailsFragment;
import org.itstep.android.lesson_25_example.fragment.MasterFragment;
import org.itstep.android.lesson_25_example.model.Note;

public class MainActivity extends ActionBarActivity
	implements MasterFragment.MasterCallback {

	private static final String TAG = MainActivity.class.getSimpleName();

	private boolean mTablet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (findViewById(R.id.details) != null) {
			mTablet = true;
		}

		getFragmentManager()
				.beginTransaction()
				.replace(R.id.container, new MasterFragment())
				.commit();


	}

	@Override
	public void onMasterListClick(final Note note) {
		Log.d(TAG, "On item click, id: " + note.getId());

		final DetailsFragment fragment = DetailsFragment.newInstance(note);

		final int targetContainer;
		if (mTablet) {
			targetContainer = R.id.details;
		} else {
			targetContainer = R.id.container;
		}

		getFragmentManager()
				.beginTransaction()
				.replace(targetContainer, fragment)
				.addToBackStack(DetailsFragment.class.getSimpleName())
				.commit();
	}
}