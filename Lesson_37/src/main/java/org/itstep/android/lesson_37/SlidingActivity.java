package org.itstep.android.lesson_37;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/*
 * SlidingActivity.java
 *
 * Created by mib on 04.03.15, 20:19
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SlidingActivity extends ActionBarActivity {

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, SlidingActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding);

        SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_layout);
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(final View panel, final float slideOffset) {

            }

            @Override
            public void onPanelOpened(final View panel) {

            }

            @Override
            public void onPanelClosed(final View panel) {

            }
        });
    }
}
