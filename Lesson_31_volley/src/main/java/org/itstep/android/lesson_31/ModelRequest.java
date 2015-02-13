package org.itstep.android.lesson_31;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

/*
 * ModelRequest.java
 *
 * Created by mib on 13.02.15, 20:55
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ModelRequest extends Request<Model> {

	private static final String URL = "http://echo.jsontest.com/one/key_one/two/key_to";

	Response.Listener<Model> mListener;

	public ModelRequest(Response.Listener<Model> listener, Response.ErrorListener lerrorListener) {
		super(Method.GET, URL, lerrorListener);

		mListener = listener;
	}

	@Override
	protected Response<Model> parseNetworkResponse(final NetworkResponse response) {
		final String json = new String(response.data);
		final Model model = new Gson().fromJson(json, Model.class);
		return Response.success(model, HttpHeaderParser.parseCacheHeaders(response));
	}

	@Override
	protected void deliverResponse(final Model response) {
		mListener.onResponse(response);
	}
}
