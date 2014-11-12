package org.itstep.android.classwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import org.itstep.android.classwork.adapter.GalleryAdapter;
import org.itstep.android.classwork.model.GalleryImage;

import java.util.ArrayList;
import java.util.List;

/*
 * CustomGalleryActivity.java
 *
 * Created by mib on 07.11.14, 20:25
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class CustomGalleryActivity extends Activity {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, CustomGalleryActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Integer[] imagesArray = {
				R.drawable.android1, R.drawable.android2,
				R.drawable.android3, R.drawable.android4,
				R.drawable.android5, R.drawable.android6,
				R.drawable.android7, R.drawable.android8,
				R.drawable.android9
		};

		List<GalleryImage> images = new ArrayList<GalleryImage>();

		GalleryImage galleryImage;
		for (int i = 0; i < imagesArray.length; i++) {
			galleryImage = new GalleryImage(
					imagesArray[i],
					String.format("Image %d", i),
					String.format("Description %d", i)
			);
			images.add(galleryImage);
		}

		GalleryAdapter adapter = new GalleryAdapter(this, R.layout.gallery_list_item, images);

		GridView gridView = new GridView(this);
		gridView.setAdapter(adapter);
		gridView.setNumColumns(GridView.AUTO_FIT);
		gridView.setColumnWidth(Utils.convertDpToPixels(this, 100));
		gridView.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);

		setContentView(gridView);
	}
}