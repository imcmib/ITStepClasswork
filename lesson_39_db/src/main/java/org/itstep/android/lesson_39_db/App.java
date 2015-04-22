package org.itstep.android.lesson_39_db;

import android.app.Application;

/*
 * App.java
 *
 * Created by aivanchenko on 22.04.2015, 14:34
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DBHelper.init(this);
    }
}