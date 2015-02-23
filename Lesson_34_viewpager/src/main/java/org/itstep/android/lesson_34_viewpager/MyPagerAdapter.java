package org.itstep.android.lesson_34_viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*
 * MyPagerAdapter.java
 *
 * Created by mib on 23.02.15, 18:12
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    public final String[] mUrls;

    public MyPagerAdapter(final FragmentManager fm, final String[] urls) {
        super(fm);

        mUrls = urls;
    }

    @Override
    public Fragment getItem(final int position) {
        return TestFragment.newInstance(mUrls[position]);
    }

    @Override
    public int getCount() {
        return mUrls.length;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return String.format("Tab %d", position);
    }
}