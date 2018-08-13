package com.example.di.apptestwunder.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.example.di.apptestwunder.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapsFragmentActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int REQUEST_ACCESS_COARSE_LOCATION = 1;
    private GoogleMap mMap;

    private ArrayList<LatLng> latLngs;
    private ArrayList<? extends String> address;
    private ArrayList<? extends String> name;
    private String position;

    double currentLongitude;
    double currentLatitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent it = getIntent();

        latLngs = it.getParcelableArrayListExtra("LAT_LONG");
        address = it.getParcelableArrayListExtra("ADDRESS");
        name = it.getParcelableArrayListExtra("NAMES");

        position = it.getStringExtra ( "POSITION" );
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled ( true );

        MarkerOptions marker = new MarkerOptions();

        for(int i= 0; i < name.size(); i++) {
            marker.title(name.get(i).toString());
            marker.snippet(address.get(i).toString());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_taxi));
            marker.position(latLngs.get(i));
            mMap.addMarker(marker);
        }

        for(int i = 0; i < name.size(); i++) {
            if (position.equals ( name.get ( i ) ) ) {
                setMarkerSelected(i);
            }
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION },
                    REQUEST_ACCESS_COARSE_LOCATION);


        } else {

            mMap.setMyLocationEnabled ( true );
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

            LocationManager lm = (LocationManager)getSystemService( Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            currentLongitude = location.getLongitude();
            currentLatitude = location.getLatitude();
        }

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {

                LatLng latLng = new LatLng(currentLatitude, currentLongitude);

                MarkerOptions marker2 = new MarkerOptions();
                marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.location));
                marker2.position(latLng);
                mMap.addMarker(marker2);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f));

                return false;
            }
        });

    }

    private void setMarkerSelected(int i) {
        MarkerOptions marker2 = new MarkerOptions();
        marker2.title(name.get(i).toString());
        marker2.snippet(address.get(i).toString());
        marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_taxi));
        marker2.position(latLngs.get(i));
        mMap.addMarker(marker2).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngs.get(i), 14f));
    }


    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_ACCESS_COARSE_LOCATION) {
            int grantResultsLength = grantResults.length;
            if(grantResultsLength > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission Granted. Please click button again to continue.", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(), "Permission denied.", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
