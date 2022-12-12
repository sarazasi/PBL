package com.example.pbl;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

public class walkFragment extends Fragment {
  MapView mapView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //レイアウトファイルを取得
    View view = inflater.inflate(R.layout.fragment_walk, container, false);
    //clickイベントリスナーを登録(運動)
    view.findViewById(R.id.btnHome2).setOnClickListener(
            v -> Navigation.findNavController(v).navigate(R.id.homeFragment)
    );
    mapView = (MapView) view.findViewById(R.id.map);
    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync(googleMap -> {
      LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
      if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
              ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // Permissionが必要です。
        return;
      }

      // 緯度と経度を取得
      Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      double latitude = location.getLatitude();
      double longitude = location.getLongitude();
      if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return;
      }
      googleMap.setMyLocationEnabled(true);
      googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.0f));
      Log.i("DEBUG", "onMapReady");
    });
    return view;
  }


  @Override
  public void onResume(){
    mapView.onResume();
    super.onResume();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

}