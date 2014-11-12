package org.itstep.android.classwork;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SimpleMenuActivity extends Activity implements View.OnClickListener {

	private static final String TAG = SimpleMenuActivity.class.getSimpleName();

	private final Handler mHandler = new Handler();
	private ActionBar mActionBar;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, SimpleMenuActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_menu);

		mActionBar = getActionBar();

		findViewById(R.id.showMenuButton).setOnClickListener(this);
		findViewById(R.id.closeMenuButton).setOnClickListener(this);
		findViewById(R.id.toggleActionBarButton).setOnClickListener(this);
	}

	/**
	 * Вызывается только при первом показе меню. Создает меню и более не используется.
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
//		menu.add("Menu 1");
//		menu.add("Menu 2");
//		menu.add("Menu 3");

//		final MenuInflater menuInflater = getMenuInflater();
//		menuInflater.inflate(R.menu.menu_simple, menu);

		getMenuInflater().inflate(R.menu.menu_simple, menu);

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Вызывается каждый раз перед отображением меню.
	 * Здесь можно вносить изменения в уже созданное меню.
	 */
	@Override
	public boolean onPrepareOptionsMenu(final Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Вызывается при клике на пункт меню.
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
		return true;
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.showMenuButton:
				openOptionsMenu();

				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						closeOptionsMenu();
					}
				}, 5000);
				break;
			case R.id.closeMenuButton:
				closeOptionsMenu();
				break;
			case R.id.toggleActionBarButton:
				if (mActionBar.isShowing()) {
					mActionBar.hide();
				} else {
					mActionBar.show();
				}
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}
