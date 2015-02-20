package org.itstep.android.lesson_33_network_image;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/*
 * App.java
 *
 * Created by mib on 20.02.15, 18:16
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class App extends Application {

    public static final int MAX_DISC_CACHE_SIZE = 50 * 1024 * 1024; // 50 Mb
    public static final int MAX_MEMORY_CACHE_SIZE = 5 * 1024 * 1024; // 5 Mb
    public DisplayImageOptions mDisplayImageOptions;

    @Override
    public void onCreate() {
        super.onCreate();

        mDisplayImageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        final ImageLoaderConfiguration configuration
                = new ImageLoaderConfiguration.Builder(this)
                .diskCacheSize(MAX_DISC_CACHE_SIZE)
                .memoryCacheSize(MAX_MEMORY_CACHE_SIZE)
                .defaultDisplayImageOptions(mDisplayImageOptions)
                .build();

        ImageLoader.getInstance().init(configuration);
    }
}