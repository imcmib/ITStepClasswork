package org.itstep.android.lesson_30_network;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/*
 * RetrofitActivity.java
 *
 * Created by mib on 06.02.15, 20:04
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class RetrofitActivity extends ActionBarActivity {

	private static final String TAG = RetrofitActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, RetrofitActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrofit);

		final RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint("http://echo.jsontest.com")
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.build();

		final JsonApiService jsonApiService =
				restAdapter.create(JsonApiService.class);

//		final Model someModel = jsonApiService.getSomeModel("One_Value", "Two_Value");

		jsonApiService.getSomeModel("One_Value", "Two_Value", new Callback<Model>() {
			@Override
			public void success(final Model model, final Response response) {
				Log.i(TAG, model.toString());
			}

			@Override
			public void failure(final RetrofitError error) {
				Log.w(TAG, "Error: " + error.getKind());
			}
		});
	}
}
