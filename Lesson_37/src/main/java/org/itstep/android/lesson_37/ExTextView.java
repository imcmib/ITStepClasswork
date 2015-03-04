package org.itstep.android.lesson_37;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/*
 * ExTextView.java
 *
 * Created by mib on 04.03.15, 18:21
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ExTextView extends TextView {

    public ExTextView(final Context context) {
        this(context, null);
    }

    public ExTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExTextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExTextView(final Context context, final AttributeSet attrs, final int defStyleAttr,
                      final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExTextView);

        final String fontName;

        final int fontId = a.getInt(R.styleable.ExTextView_customFont, 0);
        switch (fontId) {
            case 1:
                fontName = FontUtils.FONT_1;
                break;
            case 2:
                fontName = FontUtils.FONT_2;
                break;
            default:
                fontName = null;
        }

        setCustomFont(fontName);

        a.recycle();
    }

    public void setCustomFont(@FontUtils.FontName final String fontName) {
        final Typeface typeFace = FontUtils.getTypeFace(getContext(), fontName);
        setTypeface(typeFace);
    }
}