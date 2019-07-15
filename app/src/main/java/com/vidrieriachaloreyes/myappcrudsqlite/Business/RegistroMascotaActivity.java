package com.vidrieriachaloreyes.myappcrudsqlite.Business;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vidrieriachaloreyes.myappcrudsqlite.R;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.Usuario;

import java.util.ArrayList;

import static com.vidrieriachaloreyes.myappcrudsqlite.Business.Utilidades.db_version;

public class RegistroMascotaActivity extends AppCompatActivity {


    EditText nombreMascota, razaMascota;
    Spinner comboDueno;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper conn;

    Button btnRegistrarMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        razaMascota = findViewById(R.id.campoRazaMascota);
        nombreMascota = findViewById(R.id.campoNombreMascota);
        comboDueno = findViewById(R.id.comboDuenoMascota);

        btnRegistrarMascota = findViewById(R.id.btnRegistroMascota);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, db_version);

        consultarListaPersonas();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPersonas);

        comboDueno.setAdapter(adapter);

        comboDueno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRegistrarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registarMascota();
            }
        });
    }


    private void registarMascota1() {

        SQLiteDatabase db = conn.getWritableDatabase();
        int idCombo = comboDueno.getSelectedItemPosition();
        if (idCombo != 0) {
            Log.e("TAMAÑO", personasList.size() + "");
            Log.e("id combo", idCombo + "");
            Log.e("id combo - 1 ", (idCombo - 1) + "");
            //
            int idDueno = personasList.get(idCombo - 1).getId();
            //
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE_MASCOTA, nombreMascota.getText().toString());
            values.put(Utilidades.CAMPO_RAZA_MASCOTA, razaMascota.getText().toString());
            values.put(Utilidades.CAMPO_ID_DUENO, idDueno);
            //
            Long idResultante = db.insert(Utilidades.TABLA_MASCOTA, Utilidades.CAMPO_ID_MASCOTA, values);
            Toast.makeText(this, "Id Registro" + idResultante, Toast.LENGTH_SHORT).show();
            db.close();
        } else {
            Toast.makeText(this, "Debe seleccionar un dueño", Toast.LENGTH_SHORT).show();
        }

    }

    private void registarMascota() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, db_version);
        SQLiteDatabase db = conn.getWritableDatabase();

        int idCombo = comboDueno.getSelectedItemPosition();
        if (idCombo != 0) {
            int idDueno = personasList.get(idCombo - 1).getId();

            String insert = "INSERT INTO " + Utilidades.TABLA_MASCOTA
                    + "(" + Utilidades.CAMPO_NOMBRE_MASCOTA + "," + Utilidades.CAMPO_RAZA_MASCOTA + "," + Utilidades.CAMPO_ID_DUENO + ")" +
                    " VALUES ( '" + nombreMascota.getText().toString() + "','" + razaMascota.getText().toString() + "'," + idDueno + ");";
            Log.e("1 ", "------> " + insert);
            db.execSQL(insert);
            db.close();
        } else {
            Toast.makeText(this, "Debe seleccionar un dueño", Toast.LENGTH_SHORT).show();
        }


    }


    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario persona = null;
        personasList = new ArrayList<Usuario>();
        //
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);
        while (cursor.moveToNext()) {
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.e("id", persona.getId().toString());
            Log.e("Nombre", persona.getId().toString());
            Log.e("Tel", persona.getId().toString());

            personasList.add(persona);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i = 0; i < personasList.size(); i++) {
            listaPersonas.add(personasList.get(i).getId() + " - " + personasList.get(i).getNombre());
        }
    }


}
