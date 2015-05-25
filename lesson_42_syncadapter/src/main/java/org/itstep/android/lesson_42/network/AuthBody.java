package org.itstep.android.lesson_42.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * AuthBody.java
 *
 * Created by mib on 25.05.15, 18:58
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class AuthBody implements Serializable {

    @SerializedName("client_id")
    private String mClientId;

    @SerializedName("client_secret")
    private String mClientSecret;

    @SerializedName("code")
    private String mCode;

    @SerializedName("redirect_uri")
    private String mRedirectUrl;

    public AuthBody(final String clientId, final String clientSecret,
                    final String code, final String redirectUrl) {
        mClientId = clientId;
        mClientSecret = clientSecret;
        mCode = code;
        mRedirectUrl = redirectUrl;
    }

    public String getClientId() {
        return mClientId;
    }

    public String getClientSecret() {
        return mClientSecret;
    }

    public String getCode() {
        return mCode;
    }

    public String getRedirectUrl() {
        return mRedirectUrl;
    }
}
