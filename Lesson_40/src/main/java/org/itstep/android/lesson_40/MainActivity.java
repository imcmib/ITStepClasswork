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

        findViewById(R.id.cursor_loader_button).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.cursor_loader_button:
                CursorLoaderActivity.startActivity(this);
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: "
                        + getResources().getResourceName(view.getId()));
                break;
        }
    }
}
