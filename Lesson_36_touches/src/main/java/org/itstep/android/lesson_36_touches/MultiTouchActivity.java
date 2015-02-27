package org.itstep.android.lesson_36_touches;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/*
 * MultiTouchActivity.java
 *
 * Created by mib on 27.02.15, 18:39
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MultiTouchActivity extends ActionBarActivity implements View.OnTouchListener {

    private static final String TAG = MultiTouchActivity.class.getSimpleName();

    private TouchView mTouchView;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, MultiTouchActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_touch);

        mTouchView = (TouchView) findViewById(R.id.touch_view);
        mTouchView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        final int actionIndex = event.getActionIndex();
        final int pointerId = event.getPointerId(actionIndex);
        final int action = event.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i(TAG, "Down: " + pointerId);
                final PointF point = new PointF(event.getX(actionIndex), event.getY(actionIndex));
                mTouchView.addPoint(pointerId, point);
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    final PointF updatedPoint =
                            new PointF(event.getX(i), event.getY(i));

                    mTouchView.updatePoint(event.getPointerId(i), updatedPoint);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "UP: " + pointerId);
                mTouchView.removePoint(pointerId);
                break;
        }
        return true;
    }
}
