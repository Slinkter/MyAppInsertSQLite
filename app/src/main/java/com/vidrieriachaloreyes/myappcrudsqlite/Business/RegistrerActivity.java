package com.vidrieriachaloreyes.myappcrudsqlite.Business;

import android.content.ContentValues;
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

import static com.vidrieriachaloreyes.myappcrudsqlite.Business.Utilidades.db_version;

public class RegistrerActivity extends AppCompatActivity {

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
                //registrarUsuarios();
                registrarUsuariosql();

            }
        });


    }

    private void registrarUsuariosql() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, db_version);
        SQLiteDatabase db = conn.getWritableDatabase();

        //insert into usuario (id,nombre,telefono) values (123 , 'dasd','543534')
        String insert = "INSERT INTO " + Utilidades.TABLA_USUARIO
                + "(" + Utilidades.CAMPO_ID + "," + Utilidades.CAMPO_NOMBRE + "," + Utilidades.CAMPO_TELEFONO + ")" +
                " VALUES ("
                + campoID.getText().toString() + ",'" + campoNombre.getText().toString() + "','" + campoTelefono.getText().toString() + "');";
        Log.e("1 ", "------> " + insert);
        db.execSQL(insert);

        db.close();
    }


    public void registrarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, db_version);
        SQLiteDatabase db = conn.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID, campoID.getText().toString());
            values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
            values.put(Utilidades.CAMPO_TELEFONO, campoTelefono.getText().toString());

            Long idResultante = db.insert(Utilidades.CREATE_TABLA_USUARIO, Utilidades.CAMPO_ID, values);
            Toast.makeText(getApplicationContext(), "id Registro " + idResultante, Toast.LENGTH_SHORT).show();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
