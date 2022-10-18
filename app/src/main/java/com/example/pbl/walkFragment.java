package com.example.pbl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class walkFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //レイアウトファイルを取得
    View view = inflater.inflate(R.layout.fragment_walk, container, false);
    //clickイベントリスナーを登録(運動)
    view.findViewById(R.id.btnHome2).setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
              }
            }
    );
    return view;
  }
}