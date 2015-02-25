package org.itstep.android.lesson_35_animations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/*
 * SecondActivity.java
 *
 * Created by mib on 25.02.15, 19:59
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SecondActivity extends ActionBarActivity {

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, SecondActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final FirstFragment firstFragment = FirstFragment.newInstance();
        getFragmentManager().beginTransaction()
                            .replace(R.id.container, firstFragment)
                            .commit();

        findViewById(R.id.change_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.alpha_animation,
                                R.anim.alpha_animation,
                                R.anim.alpha_animation,
                                R.anim.alpha_animation)
                        .replace(R.id.container, SecondFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }
}