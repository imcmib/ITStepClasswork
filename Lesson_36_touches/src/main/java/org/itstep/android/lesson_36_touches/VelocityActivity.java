package org.itstep.android.lesson_36_touches;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

/*
 * VelocityActivity.java
 *
 * Created by mib on 27.02.15, 20:02
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class VelocityActivity extends ActionBarActivity implements View.OnTouchListener {

    private static final String TAG = VelocityActivity.class.getSimpleName();

    private VelocityTracker mVelocityTracker;
    private TextView mTextView;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, VelocityActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocity);

        mTextView = (TextView) findViewById(R.id.text_view);

        findViewById(R.id.root_view).setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mVelocityTracker.computeCurrentVelocity(1);
                final float xVelocity = mVelocityTracker.getXVelocity();

                mVelocityTracker.recycle();
                mVelocityTracker = null;

                mTextView.setText(String.valueOf(xVelocity));

                Log.i(TAG, "Velocity: " + xVelocity);
                animateTextView(xVelocity);
                break;
        }
        return true;
    }

    private void animateTextView(final float xVelocity) {
        mTextView.animate()
                 .rotationBy(360 * xVelocity)
                 .setDuration(2000)
                 .setInterpolator(new DecelerateInterpolator())
                 .start();
    }
}
