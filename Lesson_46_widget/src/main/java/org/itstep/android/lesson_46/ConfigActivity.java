package org.itstep.android.lesson_46;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class ConfigActivity extends ActionBarActivity {

    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TIME_FORMAT = "widget_time_format_";
    public final static String WIDGET_COUNT = "widget_count_";

    int mAppwidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    Intent mResultValue;
    SharedPreferences mPreferences;
    EditText mFormatEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mAppwidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        if (mAppwidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        mResultValue = new Intent();
        mResultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppwidgetId);

        setResult(RESULT_CANCELED, mResultValue);

        setContentView(R.layout.activity_config);

        mPreferences = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        String format = mPreferences.getString(WIDGET_TIME_FORMAT + mAppwidgetId, "HH:mm:ss");

        mFormatEditText = (EditText) findViewById(R.id.etFormat);
        mFormatEditText.setText(format);

        int cnt = mPreferences.getInt(ConfigActivity.WIDGET_COUNT + mAppwidgetId, -1);
        if (cnt == -1) {
            mPreferences.edit()
                        .putInt(WIDGET_COUNT + mAppwidgetId, 0)
                        .apply();
        }
    }

    public void onClick(View v){
        final String format = mFormatEditText.getText().toString();
        mPreferences.edit()
                    .putString(WIDGET_TIME_FORMAT + mAppwidgetId, format)
                    .apply();

        setResult(RESULT_OK, mResultValue);
        finish();
    }
}