package org.itstep.android.lesson_45;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragmentActivity extends ActionBarActivity implements OnMapReadyCallback {

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, MapFragmentActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_fragment);

		SupportMapFragment mapFragment =
				(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(final GoogleMap googleMap) {
		googleMap.addMarker(new MarkerOptions()
				 .position(new LatLng(0, 0))
				 .title("Marker"))
				 .setSnippet("Some text");
	}
}