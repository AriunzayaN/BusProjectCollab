package com.example.ana.busproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class BusMap extends FragmentActivity implements OnMapReadyCallback {
    LocationManager locationManager;
    private GoogleMap mMap;
    private Button button;
    private TextView text1;
    private LocationListener listener;
    Marker currmark;
    Marker busStopMarker;
    Double longitude;
    Double latitude;
    FetchJson fetchJson = new FetchJson();
    JSONObject fetchedJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_map);
        button = (Button) findViewById(R.id.button1);
        text1 = (TextView) findViewById(R.id.text1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();


                final Thread timer = new Thread() {
                    public void run() {

//                        String string = String.valueOf(latitude);
//                        Log.d("PRINT", string);
//                            URL url = new URL(string);
//                            url.openStream();

                    }
                };
                timer.start();
                text1.append("\n " + latitude + " " + longitude);
                fetchJson.doInBackground();
             if (currmark == null) {
                 currmark = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                         .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)).title("Cavalier Coach"));
                 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 17));}
             else if (currmark != null) {
                 currmark.remove();
                 currmark = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                         .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)).title("Cavalier Coach"));

                 mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));

                 busStopMarking(latitude, longitude);

                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();


    }

    public void busStopMarking(double latitude,double longitude){

        // LSB lat long approximate location
        if((latitude > 42.553633 && latitude < 42.554337)&& (longitude > -70.842969 && longitude < -70.842358) ){

             busStopMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(42.553951, -70.843091))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)).title("Bus Stop LSB"));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.553951, -70.843091), 20));

        }
        // Callahan lat long approximate location
        else if((latitude > 42.553068 && latitude < 42.553710 ) && (longitude > -70.843716 && longitude < -70.843262 )){
            BusStopAlert busStopAlert = new BusStopAlert();
            busStopAlert.show(getFragmentManager(), "Bus Stop");
            busStopMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(42.553728, -70.843230))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)).title("Bus Stop Callahan"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.553728, -70.843230), 20));
        }else if((latitude < 42.552341 && latitude > 42.552133) && (longitude < -70.842647 && longitude > -70.842673)){

            busStopMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(42.552341, -70.842647))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)).title("Bus Stop"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.552341, -70.842647), 20));

        }else if((latitude < 42.551589 && latitude > 42.551502) && (longitude < -70.841201 && longitude > -70.841657)){

            busStopMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(42.551589, -70.841201))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)).title("Bus Stop"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.551589, -70.841201), 20));

        }else if((latitude < 42.551281 && latitude > 42.551127) && (longitude < -70.841281 && longitude > -70.841464)){

            busStopMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(42.551281, -70.841281))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)).title("Bus Stop"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.551281, -70.841281), 20));

        }else if((latitude < 42.552293 && latitude > 42.551857) && (longitude < -70.837326 && longitude > -70.837752)){

            busStopMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(42.552293, -70.837326))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)).title("Bus Stop"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.552293, -70.837326), 20));

        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
//        Log.d("Map", "Initialized");
        // Add a marker in Sydney and move the camera
//        LatLng beverly = new LatLng(42, -70);
//
//        mMap.addMarker(new MarkerOptions().position(beverly).title("Somewhere near Beverly"));
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(beverly));


    }

    void configure_button() {
//         first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }


        // this code won'textView execute IF permissions are not allowed, because in the line above there is return statement.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission


                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener);
                Log.d("Button pressed", "Button pressed");



//                locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 5000, 0, listener);


            }
        });

    }

}

