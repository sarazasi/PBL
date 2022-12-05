package com.example.pbl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class excFragment extends Fragment {
    private SimpleDatabaseHelper helper = null; //databaseに接続するhelperを作成

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //レイアウトファイルを取得
        View view = inflater.inflate(R.layout.fragment_exc, container, false);
        //ホームボタンの処理
        view.findViewById(R.id.btnHome).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.homeFragment);
                    }
                }
        );

        //座ったままできる運動のボタン処理
        view.findViewById(R.id.play_sit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //databaseに値を保存
                helper = new SimpleDatabaseHelper(getActivity());

                //登録日の重複を検索
                String[] cols = {"date","point"}; //検索に用いる列を抽出
                //検索する日付を作成
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
                String[] params = {dateFormat.format(date)};

                try (SQLiteDatabase db_read = helper.getReadableDatabase();
                     Cursor cs = db_read.query("Points", cols, "date = ?",
                             params, null, null, null, null)) {
                    if (cs.moveToFirst()) { //重複していた場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 30 + cs.getInt(1)); //play_sitの運動量ポイントは30ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                        }
                    } else { //重複してない場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 30); //play_sitの運動量ポイントは50ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_ROLLBACK);
                        }
                    }
                }
                // 画面を遷移
                Navigation.findNavController(v).navigate(R.id.excMovie);
            }
        });

        //座ったままできる運動のボタン（テキスト）処理
        view.findViewById(R.id.text_play_sit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //databaseに値を保存
                helper = new SimpleDatabaseHelper(getActivity());

                //登録日の重複を検索
                String[] cols = {"date","point"}; //検索に用いる列を抽出
                //検索する日付を作成
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
                String[] params = {dateFormat.format(date)};

                try (SQLiteDatabase db_read = helper.getReadableDatabase();
                     Cursor cs = db_read.query("Points", cols, "date = ?",
                             params, null, null, null, null)) {
                    if (cs.moveToFirst()) { //重複していた場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 30 + cs.getInt(1)); //play_sitの運動量ポイントは30ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                        }
                    } else { //重複してない場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 30); //play_sitの運動量ポイントは50ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_ROLLBACK);
                        }
                    }
                }
                // 画面を遷移
                Navigation.findNavController(v).navigate(R.id.excMovie);
            }
        });

        //立って行う運動のボタン処理
        view.findViewById(R.id.play_stand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //databaseに値を保存
                helper = new SimpleDatabaseHelper(getActivity());

                //登録日の重複を検索
                String[] cols = {"date","point"}; //検索に用いる列を抽出
                //検索する日付を作成
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
                String[] params = {dateFormat.format(date)};

                try (SQLiteDatabase db_read = helper.getReadableDatabase();
                     Cursor cs = db_read.query("Points", cols, "date = ?",
                             params, null, null, null, null)) {
                    if (cs.moveToFirst()) { //重複していた場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 50 + cs.getInt(1)); //play_standの運動量ポイントは50ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                        }
                    } else { //重複してない場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 50); //play_standの運動量ポイントは50ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_ROLLBACK);
                        }
                    }
                }
                // 画面を遷移
                Navigation.findNavController(v).navigate(R.id.excMovie);
            }
        });

        //立って行う運動のボタン(テキスト)処理
        view.findViewById(R.id.text_play_hard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //databaseに値を保存
                helper = new SimpleDatabaseHelper(getActivity());

                //登録日の重複を検索
                String[] cols = {"date","point"}; //検索に用いる列を抽出
                //検索する日付を作成
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
                String[] params = {dateFormat.format(date)};

                try (SQLiteDatabase db_read = helper.getReadableDatabase();
                     Cursor cs = db_read.query("Points", cols, "date = ?",
                             params, null, null, null, null)) {
                    if (cs.moveToFirst()) { //重複していた場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 50 + cs.getInt(1)); //play_standの運動量ポイントは50ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                        }
                    } else { //重複してない場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 50); //play_standの運動量ポイントは50ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_ROLLBACK);
                        }
                    }
                }
                // 画面を遷移
                Navigation.findNavController(v).navigate(R.id.excMovie);
            }
        });

        //激しめの運動のボタン処理
        view.findViewById(R.id.play_hard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //databaseに値を保存
                helper = new SimpleDatabaseHelper(getActivity());

                //登録日の重複を検索
                String[] cols = {"date","point"}; //検索に用いる列を抽出
                //検索する日付を作成
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
                String[] params = {dateFormat.format(date)};

                try (SQLiteDatabase db_read = helper.getReadableDatabase();
                     Cursor cs = db_read.query("Points", cols, "date = ?",
                             params, null, null, null, null)) {
                    if (cs.moveToFirst()) { //重複していた場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 100 + cs.getInt(1)); //play_hardの運動量ポイントは100ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                        }
                    } else { //重複してない場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 100); //play_hardの運動量ポイントは100ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_ROLLBACK);
                        }
                    }
                }
                // 画面を遷移
                Navigation.findNavController(v).navigate(R.id.excMovie);
            }
        });

        //c激しめの運動のボタン(テキスト)処理
        view.findViewById(R.id.text_play_stand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //databaseに値を保存
                helper = new SimpleDatabaseHelper(getActivity());

                //登録日の重複を検索
                String[] cols = {"date","point"}; //検索に用いる列を抽出
                //検索する日付を作成
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
                String[] params = {dateFormat.format(date)};

                try (SQLiteDatabase db_read = helper.getReadableDatabase();
                     Cursor cs = db_read.query("Points", cols, "date = ?",
                             params, null, null, null, null)) {
                    if (cs.moveToFirst()) { //重複していた場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 100 + cs.getInt(1)); //play_hardの運動量ポイントは100ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                        }
                    } else { //重複してない場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", 100); //play_hardの運動量ポイントは100ポイント
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_ROLLBACK);
                        }
                    }
                }
                // 画面を遷移
                Navigation.findNavController(v).navigate(R.id.excMovie);
            }
        });

        //テスト（乱数生成）ボタンの処理
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //databaseに値を保存
                helper = new SimpleDatabaseHelper(getActivity());

                //登録日の重複を検索
                String[] cols = {"date","point"}; //検索に用いる列を抽出
                //検索する日付を作成
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
                String[] params = {dateFormat.format(date)};

                try (SQLiteDatabase db_read = helper.getReadableDatabase();
                     Cursor cs = db_read.query("Points", cols, "date = ?",
                             params, null, null, null, null)) {
                    if (cs.moveToFirst()) { //重複していた場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            //乱数を生成
                            int point = new Random().nextInt(100);
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", point + cs.getInt(1));
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                        }
                    } else { //重複してない場合
                        try (SQLiteDatabase db_write = helper.getWritableDatabase()) {
                            ContentValues cv = new ContentValues();
                            //乱数を生成
                            int point = new Random().nextInt(100);
                            cv.put("date", dateFormat.format(date));
                            cv.put("point", point);
                            db_read.insertWithOnConflict("Points", null, cv, SQLiteDatabase.CONFLICT_ROLLBACK);
                        }
                    }
                }
                // 画面を遷移
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });
        return view;
    }
}