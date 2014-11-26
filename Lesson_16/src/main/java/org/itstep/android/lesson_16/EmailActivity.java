package org.itstep.android.lesson_16;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import org.apache.http.protocol.HTTP;

/*
 * EmailActivity.java
 *
 * Created by MiB on 26.11.2014, 16:02
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class EmailActivity extends Activity implements View.OnClickListener {

	private static final String TAG = EmailActivity.class.getSimpleName();

	private EditText mEmailEditText;
	private EditText mSubjectEditText;
	private EditText mMessageEditText;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);

		mEmailEditText = (EditText) findViewById(R.id.emailEditText);
		mSubjectEditText = (EditText) findViewById(R.id.subjectEditText);
		mMessageEditText = (EditText) findViewById(R.id.messageEditText);

		final Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (extras.containsKey(Intent.EXTRA_EMAIL)) {
				final String[] emails = extras.getStringArray(Intent.EXTRA_EMAIL);
				final String emailsStr = TextUtils.join(", ", emails);
				mEmailEditText.setText(emailsStr);
			}

			if (extras.containsKey(Intent.EXTRA_SUBJECT)) {
				final String subject = extras.getString(Intent.EXTRA_SUBJECT);
				mSubjectEditText.setText(subject);
			}

			if (extras.containsKey(Intent.EXTRA_TEXT)) {
				final String message = extras.getString(Intent.EXTRA_TEXT);
				mMessageEditText.setText(message);
			}
		}

		findViewById(R.id.sendButton).setOnClickListener(this);
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
			case R.id.sendButton:
				sendEmail();
				break;
			default:
				Log.w(TAG, "Unhandled onClick event for view id: " + view.getId());
				break;
		}
	}

	private void sendEmail() {
		final String email = mEmailEditText.getText().toString();
		if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
			mEmailEditText.setError("Invalid email address");
			mEmailEditText.setSelection(email.length());
			mEmailEditText.requestFocus();
			return;
		}
		final String subject = mSubjectEditText.getText().toString();
		final String message = mMessageEditText.getText().toString();

		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setData(Uri.parse("mailto:"));
		intent.setType(HTTP.PLAIN_TEXT_TYPE); // "text/plain" MIME type
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
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