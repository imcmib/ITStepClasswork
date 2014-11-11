package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CheckableMenuActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, CheckableMenuActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkable_menu);
	}

	/**
	 * Вызывается только при первом показе меню. Создает меню и более не используется.
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		for (int i = 0; i < 5; i++) {
			menu.add(R.id.menu_group_main, i, Menu.NONE, String.format("Check %d", i));
		}

		for (int i = 0; i < 5; i++) {
			menu.add(R.id.menu_group_extended, i, Menu.NONE, String.format("Radio %d", i));
		}

		menu.setGroupCheckable(R.id.menu_group_main, true, false);
		menu.setGroupCheckable(R.id.menu_group_extended, true, true);

//		getMenuInflater().inflate(R.menu.menu_checkable, menu);

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
		item.setChecked(!item.isChecked());

		return super.onOptionsItemSelected(item);
	}
}
