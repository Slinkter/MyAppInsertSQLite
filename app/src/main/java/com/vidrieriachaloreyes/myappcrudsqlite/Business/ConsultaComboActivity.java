package com.vidrieriachaloreyes.myappcrudsqlite.Business;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaPersonas);
        comboPersonas.setAdapter(adapter);

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    txtDocumento.setText(personasList.get(position - 1).getId().toString());
                    txtNombre.setText(personasList.get(position - 1).getNombre());
                    txtTelefono.setText(personasList.get(position - 1).getTelefono());
                }else{
                    txtDocumento.setText("");
                    txtNombre.setText("");
                    txtTelefono.setText("");


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario persona = null;
        personasList = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);    //select * from usuarios
        while (cursor.moveToNext()) {
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.e("id", persona.getId().toString());
            Log.e("Nombre", persona.getNombre().toString());
            Log.e("Tel", persona.getTelefono().toString());

            personasList.add(persona);


        }
        obtenerLista();

    }

    private void obtenerLista() {

        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i = 0; i < personasList.size(); i++) {
            listaPersonas.add(personasList.get(i).getId() + "-" + personasList.get(i).getNombre());
        }
    }
}
