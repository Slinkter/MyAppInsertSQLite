package com.vidrieriachaloreyes.myappcrudsqlite.Business;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.vidrieriachaloreyes.myappcrudsqlite.R;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.Usuario;

import java.util.ArrayList;

public class ConsultaComboActivity extends AppCompatActivity {


    TextView txtNombre, txtDocumento, txtTelefono;
    Spinner comboPersonas;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;


    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);


        comboPersonas = (Spinner) findViewById(R.id.comboPersonas);

        txtDocumento = findViewById(R.id.txtDocumento);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);


        consultarListaPersonas();

        ArrayAdapter<CharSequence> adapter =  new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaPersonas);
        comboPersonas.setAdapter(adapter);


    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario persona = null;
        personasList = new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);
        while (cursor.moveToNext()) {
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.e("id",persona.getId().toString());
            Log.e("Nombre",persona.getNombre().toString());
            Log.e("Tel",persona.getTelefono().toString());

            personasList.add(persona);



        }
        obtenerLista();

    }

    private void obtenerLista() {

        listaPersonas =  new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i = 0 ; i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId() + "-" + personasList.get(i).getNombre());
        }
    }
}