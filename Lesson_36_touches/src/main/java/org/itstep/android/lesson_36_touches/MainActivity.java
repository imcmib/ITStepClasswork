package org.itstep.android.lesson_36_touches;

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

        findViewById(R.id.touches_detector_button).setOnClickListener(this);
        findViewById(R.id.multi_touch_button).setOnClickListener(this);
        findViewById(R.id.velocity_button).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.touches_detector_button:
                TouchesDetectorActivity.startActivity(this);
                break;
            case R.id.multi_touch_button:
                MultiTouchActivity.startActivity(this);
                break;
            case R.id.velocity_button:
                VelocityActivity.startActivity(this);
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }
}