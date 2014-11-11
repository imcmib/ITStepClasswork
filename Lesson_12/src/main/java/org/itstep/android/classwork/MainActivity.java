package org.itstep.android.classwork;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.simpleMenuButton).setOnClickListener(this);
		findViewById(R.id.groupMenuButton).setOnClickListener(this);
		findViewById(R.id.subMenuButton).setOnClickListener(this);
		findViewById(R.id.checkableMenuButton).setOnClickListener(this);
		findViewById(R.id.customMenuButton).setOnClickListener(this);
		findViewById(R.id.hasMenuButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.simpleMenuButton:
				SimpleMenuActivity.startActivity(this);
				break;
			case R.id.groupMenuButton:
				GroupMenuActivity.startActivity(this);
				break;
			case R.id.subMenuButton:
				SubMenuActivity.startActivity(this);
				break;
			case R.id.checkableMenuButton:
				CheckableMenuActivity.startActivity(this);
				break;
			case R.id.customMenuButton:
				CustomMenuActivity.startActivity(this);
				break;
			case R.id.hasMenuButton:
				checkForMenuButton();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void checkForMenuButton() {
		if (Build.VERSION.SDK_INT <= 10 || (Build.VERSION.SDK_INT >= 14 && ViewConfiguration.get(this).hasPermanentMenuKey())) {
			Toast.makeText(this, "Кнопка Menu есть", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Кнопки Menu нет", Toast.LENGTH_LONG).show();
		}
	}
}