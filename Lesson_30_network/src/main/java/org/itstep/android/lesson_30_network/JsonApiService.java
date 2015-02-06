package org.itstep.android.lesson_30_network;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/*
 * JsonApiService.java
 *
 * Created by mib on 06.02.15, 20:09
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public interface JsonApiService {

	@GET("/one/{one}/two/{two}")
	void getSomeModel(@Path("one") String one,
					  @Path("two") String two,
					  Callback<Model> callback);

	@GET("/{one}/value_one/{two}/value_two")
	Model getSomeModel(@Path("one") String one,
					  @Path("two") String two);
}
