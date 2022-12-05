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
import android.widget.Toast;

import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class homeFragment extends Fragment {
  private SimpleDatabaseHelper helper = null; //databaseの準備

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

    //databaseに接続するhelperを作成
    helper = new SimpleDatabaseHelper(getActivity());

    //選択日の運動量を表示
    /*TextView txt = view.findViewById(R.id.txtValue);
    Bundle args = requireArguments();
    txt.setText(" " + homeFragmentArgs.fromBundle(args).getNum());
    */

    //受け取った運動量をdatabaseに保存
    /*Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //dateFormat.format(date)で現在の日付の文字列を取得
    try(SQLiteDatabase db = helper.getWritableDatabase()){
      db.execSQL("INSERT INTO Points(date, point)" +
              "VALUES()");
    }*/

    //カレンダー textView
    CalendarView cal;
    cal = view.findViewById(R.id.calendarView5);
    cal.setOnDateChangeListener(
            new CalendarView.OnDateChangeListener() {
              @Override
              public void onSelectedDayChange(@NonNull CalendarView v, int year, int month, int dayOfMonth) {
                //カレンダー textView 選択日の運動量を表示
                TextView txt = view.findViewById(R.id.textbtn1);
                txt.setText(dayOfMonth + "日の運動量");

                //選択日の運動量を表示
                txt = view.findViewById(R.id.txtValue);

                //選択日の運動量を検索
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

    return view;
  }
}