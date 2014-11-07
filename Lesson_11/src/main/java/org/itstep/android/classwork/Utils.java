package org.itstep.android.classwork;

import android.content.Context;
import android.util.TypedValue;

/*
 * Utils.java
 *
 * Created by mib on 07.11.14, 19:50
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class Utils {

	public static float convertPixelsToDp(Context context, int sizeInPx) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, sizeInPx, context.getResources().getDisplayMetrics());
	}

	public static int convertDpToPixels(Context context, int sizeInDip) {
		return (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, sizeInDip,
				context.getResources().getDisplayMetrics());
	}
}
