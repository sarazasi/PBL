package com.example.pbl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

public class excFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //レイアウトファイルを取得
    View view = inflater.inflate(R.layout.fragment_exc, container, false);
    //clickイベントリスナーを登録(運動)
    view.findViewById(R.id.btnHome).setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
              }
            }
    );

    //clickイベントリスナーを登録(値受け渡し)
    view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //アクションを作成
        excFragmentDirections.ActionExcFragmentToHomeFragment action = excFragmentDirections.actionExcFragmentToHomeFragment();
        //アクションにArgumentを引き渡す
        action.setNum((new Random()).nextInt(100)); //ここの引数の値がhomeへ送られる
        //画面を遷移
        Navigation.findNavController(v).navigate(action);
      }
    });

    return view;
  }
}