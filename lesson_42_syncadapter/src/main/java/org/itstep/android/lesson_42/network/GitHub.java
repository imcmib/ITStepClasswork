package org.itstep.android.lesson_42.network;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/*
 * GitHub.java
 *
 * Created by mib on 25.05.15, 18:54
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public interface GitHub {

    @POST("/access_token")
    void auth(@Body AuthBody body, Callback<Response> callback);
}