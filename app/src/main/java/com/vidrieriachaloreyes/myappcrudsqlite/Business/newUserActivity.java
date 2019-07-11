package com.vidrieriachaloreyes.myappcrudsqlite.Business;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vidrieriachaloreyes.myappcrudsqlite.R;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;

public class newUserActivity extends AppCompatActivity {

    EditText campoID, campoNombre, campoTelefono;
    Button btnRegistraruser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        campoID = findViewById(R.id.campoid);
        campoNombre = findViewById(R.id.campoNombre);
        campoTelefono = findViewById(R.id.campoTelefono);
        btnRegistraruser = findViewById(R.id.btnRegistraruser);

        btnRegistraruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuarios();
            }
        });


    }


    public void registrarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, campoID.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, campoTelefono.getText().toString());

        Long idResultante = db.insert(Utilidades.tabla, Utilidades.CAMPO_ID, values);
        Toast.makeText(getApplicationContext(), "id Registro" + idResultante, Toast.LENGTH_SHORT).show();

        campoID.setText("");
        campoNombre.setText("");
        campoTelefono.setText("");

    }


}
