package com.mickeyco.android.navigationdrawer.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mickeyco.android.navigationdrawer.R;

public class ContactsFragment extends Fragment {


    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private  String[] PERMISSIONS = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        TextView firstAdressTextView = (TextView) rootView.findViewById(R.id.firstAdressTextView);
        TextView secondAdressTextView = (TextView) rootView.findViewById(R.id.secondAdressTextView);
        final TextView telephoneTextView = (TextView) rootView.findViewById(R.id.telephoneTextView);
        firstAdressTextView.setText(R.string.first_adress);
        secondAdressTextView.setText(R.string.second_adress);
        telephoneTextView.setText(R.string.telephone);
        telephoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = telephoneTextView.getText().toString().replaceAll("-", "");
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
            }
        });

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1 && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),
                            PERMISSIONS,PERMISSION_REQUEST_CODE);
                }
                else {
                    mGoogleMap.setMyLocationEnabled(true);
                }
                LatLng minsk = new LatLng(53.9, 27.57);
                LatLng firstAdress = new LatLng(53.93, 27.54);
                LatLng secondAdress = new LatLng(53.89, 27.58);
                mGoogleMap.addMarker(new MarkerOptions().position(firstAdress).title(getResources().getString(R.string.first_adress)));
                mGoogleMap.addMarker(new MarkerOptions().position(secondAdress).title(getResources().getString(R.string.second_adress)));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(minsk).zoom(12).build();
                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED ||
                                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                                        == PackageManager.PERMISSION_GRANTED) {
                            mGoogleMap.setMyLocationEnabled(true);
                            Toast.makeText(getActivity(), "permission accepted", Toast.LENGTH_LONG).show();
                        }
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[0])) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("This permission is important to show your location on map.")
                                .setTitle("Important permission required");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_REQUEST_CODE);
                            }
                        });
                        builder.show();
                    }
                    else {
                        Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
//                        mGoogleMap.setMyLocationEnabled(false);
                    }
                }
            return;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}
