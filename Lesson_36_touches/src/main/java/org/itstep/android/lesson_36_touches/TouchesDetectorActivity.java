package org.itstep.android.lesson_36_touches;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/*
 * ToychesDetectorActivity.java
 *
 * Created by mib on 27.02.15, 17:53
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TouchesDetectorActivity extends ActionBarActivity implements View.OnTouchListener,
        View.OnClickListener {

    private static final String TAG = TouchesDetectorActivity.class.getSimpleName();

    private View mRootView;
    private ImageView mImageView;

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, TouchesDetectorActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touches_detector);

        mRootView = findViewById(R.id.root_view);
        mRootView.setOnClickListener(this);
        mRootView.setOnTouchListener(this);

        mImageView = (ImageView) findViewById(R.id.image_view);
    }

    private PointF mDownPoint = new PointF();

    @Override
    public boolean onTouch(final View view, final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG", "ACTION_DOWN: " + event.getX() + " " + event.getY());

                mDownPoint.x = event.getX();
                mDownPoint.y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", "ACTION_MOVE: "  + event.getX() + " " + event.getY());

                final PointF movePoint = new PointF(
                        view.getX() + (event.getX() - mDownPoint.x),
                        view.getY() + (event.getY() - mDownPoint.y)
                );
                Log.i(TAG, "X: " + movePoint.x + ", Y: " + movePoint.y);
                mImageView.setX(movePoint.x);
                mImageView.setY(movePoint.y);
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG", "ACTION_UP");


                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("TAG", "ACTION_CANCEL");
                break;

        }
        return false;
    }

    @Override
    public void onClick(final View v) {
        Log.i(TAG, "onClick");
    }
}