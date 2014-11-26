package org.itstep.android.lesson_16;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import org.apache.http.protocol.HTTP;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	private static final String HTTP_GOOGLE_COM = "http://google.com";
	private static final String PHONE_NUMBER = "+380631231212";

	private CheckBox mUseChooserCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		mUseChooserCheckBox = (CheckBox) findViewById(R.id.useChooserCheckBox);

		findViewById(R.id.dialButton).setOnClickListener(this);
		findViewById(R.id.callButton).setOnClickListener(this);
		findViewById(R.id.webButton).setOnClickListener(this);
		findViewById(R.id.sendEmailButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.dialButton:
				dial(PHONE_NUMBER);
				break;
			case R.id.callButton:
				call(PHONE_NUMBER);
				break;
			case R.id.webButton:
				openUrl(HTTP_GOOGLE_COM, mUseChooserCheckBox.isChecked());
				break;
			case R.id.sendEmailButton:
				sendEmail();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void dial(final String phoneNumber) {
		final Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:" + phoneNumber));

		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		} else {
			DialogHelper.showMessageDialog(this, "Error", "No phone app found.");
		}
	}

	private void call(final String phoneNumber) {
		final Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" + phoneNumber));

		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		} else {
			DialogHelper.showMessageDialog(this, "Error", "No phone app found.");
		}
	}

	private void openUrl(final String url, final boolean useChooser) {
		final Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));

		if (intent.resolveActivity(getPackageManager()) != null) {
			if (useChooser) {
				// Create intent to show the chooser dialog
				final Intent chooser = Intent.createChooser(intent, "Select app");
				startActivity(chooser);
			} else {
				startActivity(intent);
			}
		} else {
			DialogHelper.showMessageDialog(this, "Error", "No browser found.");
		}
	}

	private void sendEmail() {
		final String email = "some_email@mail.com";
		final String subject = "Subject for test";
		final String message = "Test\nMessage";

		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setData(Uri.parse("mailto:"));
		intent.setType(HTTP.PLAIN_TEXT_TYPE); // "text/plain" MIME type
		final String[] value = {email, "email@some.com"};
		intent.putExtra(Intent.EXTRA_EMAIL, value);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, message);

		// Verify that the intent will resolve to an activity
		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		} else {
			DialogHelper.showMessageDialog(this, "Error", "No email client found.");
		}
	}
}