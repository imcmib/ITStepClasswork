package org.itstep.android.lesson_30_network;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileDownloadActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = FileDownloadActivity.class.getSimpleName();

	private EditText mUrlEditText;
	private Button mDownloadButton;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, FileDownloadActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_download);

		mUrlEditText = (EditText) findViewById(R.id.url);
		mUrlEditText.setText(
				"http://a1408.g.akamai.net/5/1408/1388/2005110403/1a1a1ad948be278cff2d96046ad90768d848b41947aa1986/sample_iPod.m4v.zip"
				+ "\n"
				+ "http://a1408.g.akamai.net/5/1408/1388/2005110403/1a1a1ad948be278cff2d96046ad90768d848b41947aa1986/sample_iTunes.mov.zip"
		);

		mDownloadButton = (Button) findViewById(R.id.start);
		mDownloadButton.setOnClickListener(this);
	}

	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
			case R.id.start:
				final String[] urls = mUrlEditText.getText().toString().split("\n");
				downloadFiles(urls);
				break;
		}
	}

	private void downloadFiles(final String[] urls) {
		if (urls.length <= 0) {
			return;
		}

		final DownloadTask downloadTask = new DownloadTask();
		downloadTask.execute(urls);
	}

	private class DownloadTask extends AsyncTask<String, String, List<String>> {
		@Override
		protected void onPreExecute() {
			mDownloadButton.setText("Downloading...");
			mDownloadButton.setEnabled(false);
		}

		@Override
		protected List<String> doInBackground(final String... params) {
			final ArrayList<String> result = new ArrayList<>(params.length);

			for (String url : params) {
				if (isCancelled()) {
					return result;
				}

				final String file = downloadUrl(url);
				if (file != null) {
					result.add(file);
				}
			}

			return result;
		}

		@Override
		protected void onProgressUpdate(final String... values) {
			mDownloadButton.setText(String.format("%s: %s%%",
					values[0], values[1]));
		}

		@Override
		protected void onPostExecute(final List<String> files) {
			mDownloadButton.setEnabled(true);
			mDownloadButton.setText("Start");

			Toast.makeText(FileDownloadActivity.this, files.size()
					+ " files downloaded", Toast.LENGTH_SHORT).show();
		}

		@Override
		protected void onCancelled(final List<String> strings) {
			mDownloadButton.setEnabled(true);
			mDownloadButton.setText("Start");

			Toast.makeText(FileDownloadActivity.this,
					"Download task cancelled", Toast.LENGTH_LONG).show();
		}

		private String downloadUrl(final String urlString) {
			InputStream is = null;
			OutputStream os = null;
			HttpURLConnection connection = null;

			try {
				final URL url = new URL(urlString);
				connection = (HttpURLConnection) url.openConnection();
				connection.connect();

				if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
					Log.w(TAG, "Server returned HTTP "
						+ connection.getResponseCode() + " "
						+ connection.getResponseMessage());
					return null;
				}

				final String fileName = urlString.substring(
						urlString.lastIndexOf('/') + 1,
						urlString.length());
				final File file = new File(getApplication().getFilesDir(), fileName);

				final int fileLength = connection.getContentLength();

				is = connection.getInputStream();
				os = new FileOutputStream(file);

				final byte data[] = new byte[4096];
				long total = 0;
				int count;
				while ((count = is.read(data)) != -1) {
					if (isCancelled()) {
						is.close();
						return null;
					}

					total += count;

					if (fileLength > 0) {
						final int progress = (int) (total * 100 / fileLength);
						publishProgress(fileName, String.valueOf(progress));
					}

					os.write(data, 0, count);
				}

				return file.getAbsolutePath();
			} catch (Exception e) {
				Log.e(TAG, "Error downloading file", e);
			} finally {
				try {
					if (os != null) os.close();
					if (is != null) is.close();
				} catch (IOException ignored) {
					// Ignore
				}

				if (connection != null) connection.disconnect();
			}

			return null;
		}
	}
}