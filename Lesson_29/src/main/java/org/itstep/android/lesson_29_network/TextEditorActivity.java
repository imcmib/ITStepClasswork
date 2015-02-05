package org.itstep.android.lesson_29_network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * TextEditorActivity.java
 *
 * Created by aivanchenko on 04.02.2015, 16:11
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TextEditorActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = TextEditorActivity.class.getSimpleName();

    private static final String FILE_NAME = "my_text.txt";

    private EditText mEditText;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, TextEditorActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_editor);

        mEditText = (EditText) findViewById(R.id.editText);

        findViewById(R.id.saveButton).setOnClickListener(this);
        findViewById(R.id.loadButton).setOnClickListener(this);
        findViewById(R.id.deleteButton).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.saveButton:
                if (saveFile()) {
                    Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case R.id.loadButton:
                loadFile();
                break;
            case R.id.deleteButton:
                if (deleteFile(FILE_NAME)) {
                    Toast.makeText(this, "File deleted", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }

    private boolean saveFile() {
		FileOutputStream fos = null;
        try {
            final String text = mEditText.getText().toString();
            final byte[] data = text.getBytes();

			fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

//            final OutputStreamWriter osw = new OutputStreamWriter(fos);
//            osw.write(text);
//            osw.close();

            fos.write(data);

            return true;
        } catch (IOException e) {
            Log.e(TAG, "Error", e);
        } finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				Log.e(TAG, "Error", e);
			}
		}

        return false;
    }

    private void loadFile() {
        try {
            final FileInputStream inputstream = openFileInput(FILE_NAME);
            if (inputstream != null) {
                final InputStreamReader isr = new InputStreamReader(inputstream);
                final BufferedReader reader = new BufferedReader(isr);
                final StringBuilder buffer = new StringBuilder();

                String str;
                while ((str = reader.readLine()) != null) {
                    buffer.append(str).append("\n");
                }

                inputstream.close();

                mEditText.setText(buffer.toString());
            }
        } catch (IOException e) {
            Log.e(TAG, "Error", e);
        }
    }
}