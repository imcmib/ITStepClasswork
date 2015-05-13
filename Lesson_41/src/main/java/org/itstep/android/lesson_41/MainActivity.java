package org.itstep.android.lesson_41;

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

        findViewById(R.id.dictionary_button).setOnClickListener(this);
        findViewById(R.id.providers_button).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.dictionary_button:
                DictionaryActivity.startActivity(this);
                break;
            case R.id.providers_button:
                ProvidersActivity.startActivity(this);
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: "
                        + getResources().getResourceName(view.getId()));
                break;
        }
    }
}