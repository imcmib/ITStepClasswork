package org.itstep.android.lesson_35_animations;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * FirstFragment.java
 *
 * Created by mib on 25.02.15, 20:14
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class SecondFragment extends Fragment {

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container,false);
    }
}