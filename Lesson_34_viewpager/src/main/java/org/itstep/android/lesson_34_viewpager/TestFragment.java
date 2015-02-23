package org.itstep.android.lesson_34_viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/*
 * TestFragment.java
 *
 * Created by mib on 23.02.15, 18:25
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TestFragment extends Fragment {

    public static final String EXTRA_KEY_TEXT = "EXTRA_KEY_TEXT";

    private String mUrl;

    public static TestFragment newInstance(final String text) {
        final Bundle args = new Bundle();
        args.putString(EXTRA_KEY_TEXT, text);

        final TestFragment fragment = new TestFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUrl = getArguments().getString(EXTRA_KEY_TEXT);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        Picasso.with(getActivity()).load(mUrl).fit().into(imageView);

        final TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(mUrl);
    }
}