package com.example.pbl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class excMovie extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //レイアウトファイルを取得
        View view = inflater.inflate(R.layout.activity_exc_movie, container, false);
        //clickイベントリスナーを登録(運動)
        view.findViewById(R.id.btnHome3).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.excFragment);
                    }
                }
        );
        return view;
    }
}