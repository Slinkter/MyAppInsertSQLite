package com.vidrieriachaloreyes.myappcrudsqlite.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.vidrieriachaloreyes.myappcrudsqlite.Business.Utilidades.tabla;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    String drop = "DROP TABLE PERSONA";

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop);
        db.execSQL(tabla);
    }
}
