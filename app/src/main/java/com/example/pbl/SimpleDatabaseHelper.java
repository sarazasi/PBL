package com.example.pbl;

import android.content.Context;
//import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//import android.database.sqlite.SQLiteStatement;

public class SimpleDatabaseHelper extends SQLiteOpenHelper {
  static final private String DBNAME = "sample.sqlite";
  static final private int VERSION = 1;

  SimpleDatabaseHelper(Context context) {
    super(context, DBNAME, null, VERSION);
  }

  @Override
  public void onOpen(SQLiteDatabase db) {
    super.onOpen(db);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE Points(" +
            "date TEXT PRIMARY KEY, point INTEGER)");
    db.execSQL("INSERT INTO Points(date, point)" +
            "VALUES ('2022-07-20', 100)");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS Points");
    onCreate(db);
  }

  public SimpleDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
    super(context, name, factory, version);
  }
}
