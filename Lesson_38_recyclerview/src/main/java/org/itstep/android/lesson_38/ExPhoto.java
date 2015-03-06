package org.itstep.android.lesson_38;

/*
 * MyModel.java
 *
 * Created by MiB on 06.03.2015, 16:57
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ExPhoto implements IPhoto {

    private String mName;

    private String mUrl;

    private String mDescription;

    public ExPhoto(final String name, final String description, final String url) {
        mName = name;
        mUrl = url;
        mDescription = description;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getUrl() {
        return mUrl;
    }

    public String getDescription() {
        return mDescription;
    }
}