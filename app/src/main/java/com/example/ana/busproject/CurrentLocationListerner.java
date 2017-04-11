package com.example.ana.busproject;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Ana on 3/29/2017.
 */

public class CurrentLocationListerner implements LocationListener {

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();

        String myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();

        //I make a log to see the results
        Log.e("MY CURRENT LOCATION", myLocation );

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
