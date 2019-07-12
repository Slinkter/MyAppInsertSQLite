package com.vidrieriachaloreyes.myappcrudsqlite.Business;

public class Utilidades {

    //constates campos tabla usuario

    public static final String TABLA_USUARIO = "usuarios";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";


    public static final String tabla = "CREATE TABLE " +
            TABLA_USUARIO + "(" + CAMPO_ID + " "
            + "INTEGER  , " + CAMPO_NOMBRE + "  TEXT," + CAMPO_TELEFONO + " TEXT)";


}
