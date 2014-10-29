package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;

/*
 * ImageViewActivity.java
 *
 * Created by aivanchenko on 29.10.2014, 12:49
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class ImageViewActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

	private ImageView mImageView;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, ImageViewActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_view);

		mImageView = (ImageView) findViewById(R.id.imageView);

		final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(final RadioGroup group, final int checkedId) {
		final ImageView.ScaleType scaleType;

		switch (checkedId) {
			case R.id.centerRadioButton:
				scaleType = ImageView.ScaleType.CENTER;
				break;
			case R.id.centerInsideRadioButton:
				scaleType = ImageView.ScaleType.CENTER_INSIDE;
				break;
			case R.id.centerCropRadioButton:
				scaleType = ImageView.ScaleType.CENTER_CROP;
				break;
			case R.id.fitCenterRadioButton:
				scaleType = ImageView.ScaleType.FIT_CENTER;
				break;
			case R.id.fitStartRadioButton:
				scaleType = ImageView.ScaleType.FIT_START;
				break;
			case R.id.fitEndRadioButton:
				scaleType = ImageView.ScaleType.FIT_END;
				break;
			case R.id.fitXYRadioButton:
				scaleType = ImageView.ScaleType.FIT_XY;
				break;
			case R.id.matrixRadioButton:
				scaleType = ImageView.ScaleType.MATRIX;
				break;
			default:
				scaleType = ImageView.ScaleType.CENTER;
		}

		mImageView.setScaleType(scaleType);
	}
}