package org.itstep.android.lesson_36_touches;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * TouchView.java
 *
 * Created by mib on 27.02.15, 18:42
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TouchView extends View {

    public static final int RADIUS = 75;

    private final Map<Integer, ExPoint> mPoints = new HashMap<>();

    private Paint mPaint;
    private Random mRandom;

    public TouchView(final Context context) {
        this(context, null);
    }

    public TouchView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();

        mRandom = new Random();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        for (Map.Entry<Integer, ExPoint> entry : mPoints.entrySet()) {
            final ExPoint exPoint = entry.getValue();
            final PointF point = exPoint.getPoint();

            mPaint.setColor(exPoint.getColor());
            mPaint.setStrokeWidth(2);
            mPaint.setTextSize(32);

            canvas.drawCircle(point.x, point.y, RADIUS, mPaint);

            canvas.drawLine(0, point.y, point.x, point.y, mPaint);
            canvas.drawLine(point.x, 0, point.x, point.y, mPaint);

            final String text = String.format("%.1f x %.1f", point.x, point.y);

            final float measureText = mPaint.measureText(text);

            canvas.drawText(text, point.x - measureText - RADIUS, point.y - RADIUS, mPaint);
        }
    }

    public void addPoint(final int index, final PointF point) {
        final int color = Color.argb(255, mRandom.nextInt(255), mRandom.nextInt(255),
                mRandom.nextInt(255));

        mPoints.put(index, new ExPoint(point, color));

        invalidate();
    }

    public void removePoint(final int index) {
        mPoints.remove(index);

        invalidate();
    }

    public void updatePoint(final int pointerId, final PointF updatedPoint) {
        final ExPoint exPoint = mPoints.get(pointerId);
        exPoint.setPoint(updatedPoint);

        invalidate();
    }

    public static class ExPoint {

        private PointF mPoint;
        private int mColor;

        public ExPoint(final PointF point, final int color) {
            mPoint = point;
            mColor = color;
        }

        public PointF getPoint() {
            return mPoint;
        }

        public int getColor() {
            return mColor;
        }

        public void setColor(final int color) {
            mColor = color;
        }

        public void setPoint(final PointF point) {
            mPoint = point;
        }
    }
}