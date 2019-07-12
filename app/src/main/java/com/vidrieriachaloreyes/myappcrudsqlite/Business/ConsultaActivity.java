package com.vidrieriachaloreyes.myappcrudsqlite.Business;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vidrieriachaloreyes.myappcrudsqlite.R;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;

public class ConsultaActivity extends AppCompatActivity  {


    EditText campoId, campoNombre, campoTelefono;
    Button btnConsuta;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);


        campoId = findViewById(R.id.documentoID);
        campoNombre = findViewById(R.id.campoNombreConsulta);
        campoTelefono = findViewById(R.id.campoTelefonoConsulta);

        btnConsuta = findViewById(R.id.btnConsultar);

        btnConsuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // consultar();
                consultarSql();
            }
        });


    }

    private void consultarSql() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {campoId.getText().toString()};
        try {
            //select nombre telefono from usuarios where codigo =?;
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_NOMBRE + "," +Utilidades.CAMPO_TELEFONO +
                    " FROM " + Utilidades.TABLA_USUARIO + " WHERE " + Utilidades.CAMPO_ID+" = ?" ,parametros);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));


        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "No existe el documento", Toast.LENGTH_SHORT).show();
            Log.e("error", "el documento no existe");
            limpiar();
        }
    }


    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {campoId.getText().toString()};
        String[] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_ID + "=?", parametros, null, null, null, null);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "No existe el documento", Toast.LENGTH_SHORT).show();
            Log.e("error", "el documento no existe");
            limpiar();
        }


    }

    private void limpiar() {
        campoNombre.setText("");
        campoTelefono.setText("");
    }

}
