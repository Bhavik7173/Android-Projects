package com.gi.mapapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.gi.mapapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    MarkerOptions markerOptions, markerOptions1;
    Dialog dialog;
    double lat, lan;
    String zoom;
    private TextView tvLocation;
    private GoogleApiClient mGoogleApiClient;
    private Polyline currentPolyline;
    Marker marker = null;
    LocationManager locationManager;
    String[] PERMISSION = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    Button mapDirection;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        markerOptions = new MarkerOptions().position(new LatLng(0, 0)).title("My Current Location");
        markerOptions1 = new MarkerOptions().position(new LatLng(40.8666, 74.1976)).title("My Destination Location");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapDirection = findViewById(R.id.btnDirection);
        mapDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDailog();

            }
        });


        if (Build.VERSION.SDK_INT >= 23 && !isPermissionGranted()) {
            requestPermissions(PERMISSION, 1);
        } else {
            requestLocation();
        }
        if (!isLocationEnabled()) {
            showAlert(1);
        }

        tvLocation = (TextView) findViewById((R.id.tv));

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void myDailog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        dialog.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.string.destination);
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        EditText latEd = dialog.findViewById(R.id.lat_ed);
        EditText lanEd = dialog.findViewById(R.id.lan_ed);
        Spinner zoomSp = dialog.findViewById(R.id.zoomsp);

        Button submitBtn = dialog.findViewById(R.id.submitbtn);
        Button resetBtn = dialog.findViewById(R.id.resetbtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lat = Double.parseDouble(latEd.getText().toString());
                lan = Double.parseDouble(lanEd.getText().toString());
                zoom = zoomSp.getSelectedItem().toString();
                dialog.dismiss();
                LatLng msu1 = new LatLng(lat, lan);
                mMap.addMarker(new MarkerOptions().position(msu1).title("My Destination Location"));
                int zoomlevel = 1;

                if (zoom.equalsIgnoreCase("world"))
                    zoomlevel = 1;
                else if (zoom.equalsIgnoreCase("Landmass/continent"))
                    zoomlevel = 5;
                else if (zoom.equalsIgnoreCase("City"))
                    zoomlevel = 10;
                else if (zoom.equalsIgnoreCase("Streets"))
                    zoomlevel = 15;
                else if (zoom.equalsIgnoreCase("Buildings"))
                    zoomlevel = 20;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(msu1, zoomlevel));

            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latEd.setText("");
                lanEd.setText("");
                zoomSp.setSelection(0);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapDirection.setEnabled(true);
        mMap.clear();
        Log.d("mylog", "Added Markers");
        mMap.addMarker(markerOptions);
        mMap.addMarker(markerOptions1);

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(40.8666, 74.1976))
                .zoom(7)
                .bearing(0)
                .tilt(45)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 5000, null);
    }
    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_api);
        return url;
    }

//
    @Override
    public void onLocationChanged(@NonNull Location location) {
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    private void requestLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 10000, 10, this);
    }

    private boolean isLocationEnabled() {
        return this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isPermissionGranted() {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                ==
                PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.v("mylog", "Permission Is Granted");
            return true;
        } else {
            Log.v("mylog", "Permission Is Not Granted");
            return false;
        }
    }

    private void showAlert(final int status) {
        String message, title, btnText;
        if (status == 1) {
            message = "Your Location Settings is set to 'OFF'.\nPlease Enable Location to" + "use this app";
            title = "Enable Location";
            btnText = "Location Settings";
        } else {
            message = "Please allow this app to access location";
            title = "Permission Access";
            btnText = "Grant";
        }
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(false);
        alertDialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (status == 1) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        } else {
                            requestPermissions(PERMISSION, 1);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

        alertDialog.show();


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}