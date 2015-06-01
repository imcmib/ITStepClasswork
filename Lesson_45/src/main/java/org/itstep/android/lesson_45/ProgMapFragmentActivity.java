package org.itstep.android.lesson_45;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class ProgMapFragmentActivity extends Activity implements
        OnMapReadyCallback, View.OnClickListener, OnStreetViewPanoramaReadyCallback {

    private static final String TAG = ProgMapFragmentActivity.class.getSimpleName()
            ;
    private GoogleMap mMap;
    public static final LatLng LAT_LNG_LONDON = new LatLng(51.5286416, -0.1015987);
    public static final LatLng LAT_LNG_BERLIN = new LatLng(52.519503, 13.407510);
    public static final LatLng LAT_LNG_PARIS = new LatLng(48.858859,2.3470599);

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, ProgMapFragmentActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog_map_fragment);

        findViewById(R.id.button_normal).setOnClickListener(this);
        findViewById(R.id.button_satellite).setOnClickListener(this);
        findViewById(R.id.button_hybrid).setOnClickListener(this);
        findViewById(R.id.button_terrain).setOnClickListener(this);
        findViewById(R.id.button_london).setOnClickListener(this);
        findViewById(R.id.button_street).setOnClickListener(this);

        MapFragment mapFragment = MapFragment.newInstance();

        getFragmentManager().beginTransaction()
                .add(R.id.map_container, mapFragment)
                .commit();

        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        final UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setAllGesturesEnabled(true);

        mMap.addMarker(
                new MarkerOptions().position(LAT_LNG_LONDON).snippet("Snippet").title("London"));

        mMap.addMarker(
                new MarkerOptions().position(LAT_LNG_BERLIN).snippet("Snippet").title("Berlin")
                                   .draggable(true));

        mMap.addPolyline(new PolylineOptions().add(LAT_LNG_LONDON, LAT_LNG_BERLIN));

        mMap.addPolygon(new PolygonOptions().add(LAT_LNG_LONDON, LAT_LNG_BERLIN, LAT_LNG_PARIS)
                                            .fillColor(Color.parseColor("#ff0000"))
                                            .strokeColor(Color.parseColor("#00ff00"))
                                            .strokeWidth(3));
        mMap.addCircle(new CircleOptions()
                            .center(LAT_LNG_PARIS)
                            .radius(1500)
                            .fillColor(Color.parseColor("#0000ff"))
                            .zIndex(9999));

        Toast.makeText(this, "Map ready", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(final View view) {
        if (mMap == null) {
            return;
        }

        switch (view.getId()) {
            case R.id.button_normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.button_hybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.button_satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.button_terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.button_london:
                goLondon();
                break;
            case R.id.button_street:
                goToStreetView();
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: " + getResources()
                        .getResourceName(view.getId()));
                break;
        }
    }

    private void goToStreetView() {
        final StreetViewPanoramaOptions options = new StreetViewPanoramaOptions();
        options.position(LAT_LNG_LONDON);

        StreetViewPanoramaFragment fragment = StreetViewPanoramaFragment.newInstance(options);
        getFragmentManager().beginTransaction()
                            .replace(R.id.map_container, fragment)
                            .commit();

        fragment.getStreetViewPanoramaAsync(this);
    }

    private void goLondon() {
        //        final CameraUpdate cm = CameraUpdateFactory.newLatLngZoom(latLngLondon, 11);

        final LatLngBounds bounds = new LatLngBounds(LAT_LNG_LONDON, LAT_LNG_BERLIN);

        final CameraUpdate cm = CameraUpdateFactory.newLatLngBounds(bounds, 50);

        mMap.animateCamera(cm, 4000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                Toast.makeText(ProgMapFragmentActivity.this, "onFinish", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(ProgMapFragmentActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStreetViewPanoramaReady(final StreetViewPanorama streetViewPanorama) {

    }
}