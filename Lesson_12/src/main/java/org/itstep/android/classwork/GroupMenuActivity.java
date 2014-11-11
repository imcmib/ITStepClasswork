package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class GroupMenuActivity extends Activity {

	private EditText mEditText;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, GroupMenuActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_menu);

		mEditText = (EditText) findViewById(R.id.editText);
		mEditText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
			@Override
			public boolean onCreateActionMode(final ActionMode mode, final Menu menu) {
				return false;
			}

			@Override
			public boolean onPrepareActionMode(final ActionMode mode, final Menu menu) {
				return false;
			}

			@Override
			public boolean onActionItemClicked(final ActionMode mode, final MenuItem item) {
				return false;
			}

			@Override
			public void onDestroyActionMode(final ActionMode mode) {

			}
		});
	}

	/**
	 * Вызывается только при первом показе меню. Создает меню и более не используется.
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		menu.add(R.id.menu_group_main, R.id.menu_add, 0, "Add");
		menu.add(R.id.menu_group_main, R.id.menu_edit, 0, "Edit");
		menu.add(R.id.menu_group_main, R.id.menu_delete, 3, "Delete");
		menu.add(R.id.menu_group_extended, R.id.menu_copy, 1, "Copy");
		menu.add(R.id.menu_group_extended, R.id.menu_paste, 2, "Paste");
		menu.add(R.id.menu_group_main, R.id.menu_exit, 4, "Exit");

//		getMenuInflater().inflate(R.menu.menu_group, menu);

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Вызывается каждый раз перед отображением меню.
	 * Здесь можно вносить изменения в уже созданное меню.
	 */
	@Override
	public boolean onPrepareOptionsMenu(final Menu menu) {
		final int selectionStart = mEditText.getSelectionStart();
		final int selectionEnd = mEditText.getSelectionEnd();
		final int selectedCharsCount = selectionEnd - selectionStart;

		menu.setGroupVisible(R.id.menu_group_extended, selectedCharsCount > 0);

		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Вызывается при клике на пункт меню.
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_add:
			case R.id.menu_edit:
			case R.id.menu_delete:
			case R.id.menu_copy:
			case R.id.menu_paste:
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menu_exit:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
