package com.example.pbl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class homeFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    //レイアウトファイルを取得
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    //clickイベントリスナーを登録(運動)
    view.findViewById(R.id.btnExc).setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.excFragment);
              }
            }
    );

    //clickイベントリスナーを登録(ウォーキング)
    view.findViewById(R.id.btnWalk).setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.walkFragment); //ボタンを押すとウォーキング画面に遷移
              }
            }
    );

    return view;
  }
}