package com.example.pbl;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

public class walkFragment extends Fragment {
  MapView mapView;
  double distance = 0;
  // 前回の更新時の位置情報
  Location lastLocation = null;
  LocationListener locationListener = null;
  GoogleMap googleMap;
  @SuppressLint("MissingInflatedId")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //レイアウトファイルを取得
    View view = inflater.inflate(R.layout.fragment_walk, container, false);
    //clickイベントリスナーを登録(運動)
    view.findViewById(R.id.btnHome2).setOnClickListener(
            //homeアクティビティに遷移
            v -> Navigation.findNavController(v).navigate(R.id.homeFragment)
    );
    // Mapviewを取得
    mapView = (MapView) view.findViewById(R.id.map);
    mapView.onCreate(savedInstanceState);

    //Mapviewを表示
    mapView.getMapAsync(gMap -> {
      googleMap = gMap;
      LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
      if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
              ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // Permissionが必要です。
        return;
      }
      // 緯度と経度を取得
      Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      double latitude = location.getLatitude();
      double longitude = location.getLongitude();
      if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        return;
      }
      gMap.setMyLocationEnabled(true);
      gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.0f));
      Log.i("DEBUG", "onMapReady");
    });
    LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);

    // clickイベントリスナーを登録(移動距離記録開始)
    view.findViewById(R.id.start).setOnClickListener(v -> {
      lastLocation = null;
      // 移動距離の記録を開始するコード
      locationListener = new LocationListener() {
        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        @Override
        public void onLocationChanged(Location location) {
          // 前回の更新時の位置情報が存在する場合
          if (lastLocation != null) {
            // 移動距離を計算
            distance += location.distanceTo(lastLocation);
          }
          // 位置情報を更新
          lastLocation = location;
          // 移動距離を表示するためのViewを取得
          TextView distanceView = (TextView) view.findViewById(R.id.distanceView);
          // 移動距離を表示
          distanceView.setText("移動距離: " + String.format("%.1f", distance) + "m");
          double latitude = location.getLatitude();
          double longitude = location.getLongitude();
          // 地図の中心を更新
          googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }
        @Override
        public void onProviderEnabled(String provider) { }
        @Override
        public void onProviderDisabled(String provider) { }
      };
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    });
    // clickイベントリスナーを登録(移動距離記録終了)
    view.findViewById(R.id.stop).setOnClickListener(v -> locationManager.removeUpdates(locationListener));
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