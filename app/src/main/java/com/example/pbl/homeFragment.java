package com.example.pbl;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;

public class homeFragment extends Fragment {
  private SimpleDatabaseHelper helper = null; //databaseに接続するhelperを作成

  @SuppressLint("SetTextI18n")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    //レイアウトファイルを取得
    View view = inflater.inflate(R.layout.fragment_home, container, false);

    //運動画面に移るボタンの処理
    view.findViewById(R.id.btnExc).setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.excFragment);
              }
            }
    );

    //ウォーキング画面に移るボタンの処理
    view.findViewById(R.id.btnWalk).setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.walkFragment); //ボタンを押すとウォーキング画面に遷移
              }
            }
    );

    // TODO: 2022/12/06 databaseの整理(登録から一週間経過したデータを消す) 
    



    //カレンダー textView
    CalendarView cal;
    cal = view.findViewById(R.id.calendarView5);
    cal.setOnDateChangeListener(
            new CalendarView.OnDateChangeListener() {
              @Override
              public void onSelectedDayChange(@NonNull CalendarView v, int year, int month, int dayOfMonth) {
                //カレンダー textView 選択日の運動量を表示
                TextView txt = view.findViewById(R.id.text_play_sit);
                txt.setText(dayOfMonth + "日の運動量");

                //選択日の運動量を表示
                txt = view.findViewById(R.id.txtValue);

                //選択日の運動量を検索
                helper = new SimpleDatabaseHelper(getActivity());
                String[] cols = {"date","point"}; //検索に用いる列を抽出
                String[] params = {String.valueOf(year) + '-' + String.valueOf(month + 1) + '-' + String.valueOf(dayOfMonth)}; //検索する日付を作成
                try (SQLiteDatabase db = helper.getReadableDatabase();
                     Cursor cs = db.query("Points", cols, "date = ?",
                             params, null, null, null, null)) {
                  if (cs.moveToFirst()) {
                    txt.setText(cs.getString(1));
                  } else {
                    txt.setText(" - ");
                  }
                }
              }
            }
    );

    //カレンダーの選択可能日を過去一週間に設定
    //下限
    Calendar min = Calendar.getInstance();
    min.add(Calendar.DATE, -7);
    long minTimeInMills = min.getTimeInMillis();

    //上限
    Calendar max = Calendar.getInstance();
    max.add(Calendar.DATE, 0);
    long maxTimeInMillis = max.getTimeInMillis();

    cal.setMinDate(minTimeInMills);
    cal.setMaxDate(maxTimeInMillis);

    return view;
  }
}