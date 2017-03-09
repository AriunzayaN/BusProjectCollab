package com.example.ana.busproject;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.Manifest;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

//    private void PerrmissionWork() {
//
//        List<String> permissionsNeeded = new ArrayList<String>();
//
//        final List<String> permissionsList = new ArrayList<String>();
//        if (!addPermission(permissionsList,
//                Manifest.permission.ACCESS_FINE_LOCATION))
//            permissionsNeeded.add("GPS");
//        if (!addPermission(permissionsList,
//                Manifest.permission.ACCESS_COARSE_LOCATION))
//            permissionsNeeded.add("GPS COARSE");
//
//
//        if (permissionsList.size() > 0) {
//            if (permissionsNeeded.size() > 0) {
//                // Need Rationale
//                String message = "You need to grant access to "
//                        + permissionsNeeded.get(0);
//                for (int i = 1; i < permissionsNeeded.size(); i++)
//                    message = message + ", " + permissionsNeeded.get(i);
//                showMessageOKCancel(message,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                requestPermissions(permissionsList
//                                                .toArray(new String[permissionsList
//                                                        .size()]),
//                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
//                            }
//                        });
//                return;
//            }
//            requestPermissions(
//                    permissionsList.toArray(new String[permissionsList.size()]),
//                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
//            return;
//        }
//        splashMainWork();
//    }
//
//// mapWork();
//
//    private boolean addPermission(List<String> permissionsList,
//                                  String permission) {
//        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
//            permissionsList.add(permission);
//            // Check for Rationale Option
//            if (!shouldShowRequestPermissionRationale(permission))
//                return false;
//        }
//        return true;
//    }
//
//    private void showMessageOKCancel(String message,
//                                     android.content.DialogInterface.OnClickListener onClickListener) {
//        new AlertDialog.Builder(context).setMessage(message)
//                .setPositiveButton("OK", onClickListener).setCancelable(false)
//                .setNegativeButton("Cancel", null).create().show();
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
//                Map<String, Integer> perms = new HashMap<String, Integer>();
//                // Initial
//                perms.put(Manifest.permission.ACCESS_FINE_LOCATION,
//                        PackageManager.PERMISSION_GRANTED);
//                perms.put(Manifest.permission.ACCESS_COARSE_LOCATION,
//                        PackageManager.PERMISSION_GRANTED);
//
//                // Fill with results
//                for (int i = 0; i < permissions.length; i++)
//                    perms.put(permissions[i], grantResults[i]);
//                // Check for ACCESS_FINE_LOCATION
//                if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                        && perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                        ) {
//                    // All Permissions Granted
//                    splashMainWork();
//                } else {
//                    // Permission Denied
//                    Toast.makeText(context, "Some Permission is Denied",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//            break;
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions,
//                        grantResults);
//        }
//    }
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

        // Add a marker in Sydney and move the camera
        LatLng beverly = new LatLng(42, -70);
        mMap.addMarker(new MarkerOptions().position(beverly).title("Marker in Beverly"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(beverly));
    }


}

