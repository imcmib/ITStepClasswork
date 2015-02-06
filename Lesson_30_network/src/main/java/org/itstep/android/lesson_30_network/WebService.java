package org.itstep.android.lesson_30_network;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebService extends IntentService {

	public static final String EXTRA_KEY_URL = "EXTRA_KEY_URL";
	public static final String EXTRA_KEY_HTML = "EXTRA_KEY_HTML";

	public static final String ACTION_DOWNLOAD_COMPLETED = "ACTION_DOWNLOAD_COMPLETED";

	private static final String TAG = WebService.class.getSimpleName();

	public WebService() {
		super(WebService.class.getSimpleName());

		Log.i(TAG, WebService.class.getSimpleName() + " started");
	}

	@Override
	protected void onHandleIntent(final Intent intent) {
		Log.i(TAG, "onHandleIntent: " + intent.toString());

		final String url = intent.getStringExtra(EXTRA_KEY_URL);
		Log.i(TAG, "URL: " + url);

		final String html = loadHtml(url);
		Log.i(TAG, "HTML: " + html);

		final Intent broadcastIntent = new Intent(ACTION_DOWNLOAD_COMPLETED);
		broadcastIntent.putExtra(EXTRA_KEY_HTML, html);

		sendBroadcast(broadcastIntent);
	}

	private String loadHtml(String urlString) {
		InputStream inputStream = null;
		HttpURLConnection connection = null;

		try {
			final URL url = new URL(urlString);

			connection = (HttpURLConnection) url.openConnection();
			connection.connect();

			Log.v(TAG, "Connection opened, code: " + connection.getResponseCode());
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				Log.w(TAG, "Connection error: " + connection.getResponseCode()
						+ ", " + connection.getResponseMessage());
				return null;
			}

			inputStream = connection.getInputStream();

			final StringBuilder html = new StringBuilder();

			final byte[] buffer = new byte[4096];

			while (inputStream.read(buffer) != -1) {
				html.append(new String(buffer));
			}

			return html.toString();
		} catch (Exception e) {
			Log.e(TAG, "Error", e);
		} finally {
			try {
				if (inputStream != null) inputStream.close();
			} catch (IOException e) {
				// Ignore
			}
			if (connection != null) connection.disconnect();
		}

		return null;
	}
}
