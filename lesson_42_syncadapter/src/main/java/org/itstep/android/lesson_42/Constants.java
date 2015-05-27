package org.itstep.android.lesson_42;

import android.net.Uri;

/*
 * Constants.java
 *
 * Created by mib on 18.02.15, 18:52
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class Constants {

    private Constants() {
        // No instance
    }

    public static final String ACCOUNT = "my_account";
    public static final String TOKEN_TYPE = "bearer";

    public static final String AUTH_REDIRECT_URL = "http://localhost/auth/success";
    public static final String TOKEN_REDIRECT_URL = "http://localhost/token/success";

    public static final String CLIENT_ID = "f4ea19b1d1c6117db4b3";
    public static final String CLIENT_SECRET =
            "42d234b100591fce54bb55bc7960e0a0379e4108";


    public static final String AUTH_BASE_URL = "https://github.com/login/oauth";
    private static final String AUTH_URL = AUTH_BASE_URL + "/authorize";

    public static final String BASE_URL = "https://api.github.com";

    public static String getAuthUrl() {
        final String scope = "user";

        return Uri.parse(AUTH_URL)
           .buildUpon()
           .appendQueryParameter("client_id", CLIENT_ID)
           .appendQueryParameter("redirect_uri", AUTH_REDIRECT_URL)
           .appendQueryParameter("scope", scope)
           .appendQueryParameter("state", "rnd")
           .build().toString();
    }
}