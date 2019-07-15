package com.vidrieriachaloreyes.myappcrudsqlite.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vidrieriachaloreyes.myappcrudsqlite.Business.Utilidades;

import static com.vidrieriachaloreyes.myappcrudsqlite.Business.Utilidades.CREATE_TABLA_USUARIO;
import static com.vidrieriachaloreyes.myappcrudsqlite.Business.Utilidades.CREATE_TABLA_MASCOTA;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    String drop_usuario = "DROP TABLE IF EXISTS " + Utilidades.TABLA_USUARIO;
    String drop_mascota = "DROP TABLE IF EXISTS " + Utilidades.TABLA_MASCOTA;

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLA_USUARIO);
        db.execSQL(CREATE_TABLA_MASCOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop_usuario);
        db.execSQL(drop_mascota);
        onCreate(db);
    }
}
