package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class CustomMenuActivity extends Activity implements View.OnClickListener {

	private static final String TAG = CustomMenuActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, CustomMenuActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_menu);
	}

	/**
	 * Вызывается только при первом показе меню. Создает меню и более не используется.
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.menu_custom, menu);

		final MenuItem menuItem = menu.findItem(R.id.menu_custom);
		final View actionView = menuItem.getActionView();
		actionView.findViewById(R.id.menuButton).setOnClickListener(this);

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
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.menuButton:
				Toast.makeText(this, "Menu Button Click!", Toast.LENGTH_SHORT).show();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}
