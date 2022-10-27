package com.example.pbl;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class homeFragment extends Fragment {
  @SuppressLint("SetTextI18n")
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

    TextView txt = view.findViewById(R.id.txtValue);
    Bundle args = requireArguments();
    txt.setText("乱数" + homeFragmentArgs.fromBundle(args).getNum());

    //カレンダー textView
    CalendarView cal;
    cal = view.findViewById(R.id.calendarView5);
    cal.setOnDateChangeListener(
            new CalendarView.OnDateChangeListener() {
              @Override
              public void onSelectedDayChange(@NonNull CalendarView v, int year, int month, int dayOfMonth) {
                //カレンダー textView
                TextView txt = view.findViewById(R.id.textView);
                txt.setText(dayOfMonth + "日の運動量");
              }
            }
    );

    return view;

  }
}