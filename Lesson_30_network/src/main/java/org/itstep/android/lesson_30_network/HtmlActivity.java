package org.itstep.android.lesson_30_network;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class HtmlActivity extends ActionBarActivity {

	private static final String TAG = HtmlActivity.class.getSimpleName();

	private EditText mHtml;
	private MyBroadcastReceiver mMyBroadcastReceiver;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, HtmlActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_html);

		final EditText url = (EditText) findViewById(R.id.url);
		url.setText("http://echo.jsontest.com/key/value/one/two");

		mHtml = (EditText) findViewById(R.id.html);

		findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				final String urlString = url.getText().toString();

				final Intent intent = new Intent(HtmlActivity.this, WebService.class);
				intent.putExtra(WebService.EXTRA_KEY_URL, urlString);

				startService(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();

		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(WebService.ACTION_DOWNLOAD_COMPLETED);

		mMyBroadcastReceiver = new MyBroadcastReceiver();

		registerReceiver(mMyBroadcastReceiver, intentFilter);
	}

	@Override
	protected void onStop() {
		super.onStop();

		unregisterReceiver(mMyBroadcastReceiver);
	}

	private class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(final Context context, final Intent intent) {
			Log.d(TAG, "Received:" + intent.toString());
			final String json = intent.getStringExtra(WebService.EXTRA_KEY_HTML);
			mHtml.setText(json);

			try {
				final JSONObject jsonObject = new JSONObject(json);

				final String one = jsonObject.getString("one");
				final String key = jsonObject.getString("key");

				final Model model = new Model(one, key);

				Log.i(TAG, model.toString());
			} catch (JSONException e) {
				Log.w(TAG, "Error parsing json", e);
			}
		}
	}
}