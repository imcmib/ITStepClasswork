package org.itstep.android.lesson19;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.itstep.android.lesson19.receiver.SimpleReceiver;

public class MainActivity extends Activity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.simpleReceiverButton).setOnClickListener(this);
		findViewById(R.id.sendBroadcastButton).setOnClickListener(this);
		findViewById(R.id.localReceiverButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.simpleReceiverButton:
				SimpleReceiverActivity.startActivity(this);
				break;
			case R.id.sendBroadcastButton:
				final Intent intent = new Intent(SimpleReceiver.ACTION_NEW_MESSAGE);
				intent.putExtra(SimpleReceiver.EXTRA_KEY_MESSAGE, "Some message!");
				sendBroadcast(intent);
				break;
			case R.id.localReceiverButton:
				LocalReceiverActivity.startActivity(this);
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}
}