package org.itstep.android.lesson_40;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_content_provider).setOnClickListener(this);
        findViewById(R.id.button_phone_book).setOnClickListener(this);
        findViewById(R.id.button_calls_log).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.button_content_provider:
                ContentProviderActivity.startActivity(this);
                break;
            case R.id.button_phone_book:
                PhoneBookActivity.startActivity(this);
                break;
            case R.id.button_calls_log:
                PhoneCallsActivity.startActivity(this);
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " +
                        getResources().getResourceName(view.getId()));
                break;
        }
    }
}