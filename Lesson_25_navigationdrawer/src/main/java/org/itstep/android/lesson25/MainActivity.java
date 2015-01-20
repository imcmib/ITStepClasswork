package org.itstep.android.lesson25;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;

import org.itstep.android.lesson25.fragment.SimpleFragment;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private static final String TAG = MainActivity.class.getSimpleName();

	private NavigationDrawerFragment mNavigationDrawerFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment)
				getFragmentManager().findFragmentById(R.id.navigation_drawer);

		final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		final Fragment fragment;

		switch (position) {
			case 0:
				getSupportActionBar().setTitle("Title 1");
				fragment = SimpleFragment.newInstance("Fragment 1");
				break;
			case 1:
				getSupportActionBar().setTitle("Title 2");
				fragment = SimpleFragment.newInstance("Fragment 2");
				break;
			case 2:
				getSupportActionBar().setTitle("Title 3");
				fragment = SimpleFragment.newInstance("Fragment 3");
				break;
			default:
				Log.w(TAG, "Unknown position: " + position);
				return;
		}

		getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}
}