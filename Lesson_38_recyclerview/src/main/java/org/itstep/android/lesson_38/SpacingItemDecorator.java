package org.itstep.android.lesson_38;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/*
 * SpacingItemDecorator.java
 *
 * Created by mib on 06.03.15, 20:16
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SpacingItemDecorator extends RecyclerView.ItemDecoration {

    @Override
    public void onDraw(final Canvas c, final RecyclerView parent,
                       final RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(final Canvas c, final RecyclerView parent,
                           final RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(final Rect outRect, final View view,
                               final RecyclerView parent, final RecyclerView.State state) {
        outRect.set(20, 20, 20, 20);
    }
}