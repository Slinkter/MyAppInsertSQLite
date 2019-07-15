package com.vidrieriachaloreyes.myappcrudsqlite.Business;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vidrieriachaloreyes.myappcrudsqlite.R;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.Mascota;

import java.util.ArrayList;

import static com.vidrieriachaloreyes.myappcrudsqlite.Business.Utilidades.db_version;

public class ListaMascotaActivity extends AppCompatActivity {

    ListView listViewMascota;
    ArrayList<String> listaInformacion;
    ArrayList<Mascota> listaMascotas;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascota);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,db_version);
        listViewMascota = findViewById(R.id.listViewMascota);
        consultarListaPersonas();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewMascota.setAdapter(adapter);
        listViewMascota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mascota mascota = listaMascotas.get(position);
                Intent intent =  new Intent(ListaMascotaActivity.this,DetalleMascotaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("mascota",mascota);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Mascota mascota = null;
        listaMascotas =  new ArrayList<Mascota>();
        //select * from usuarios
        Cursor cursor =  db.rawQuery("SELECT * FROM " + Utilidades.TABLA_MASCOTA,null);
        while (cursor.moveToNext()){
            mascota =  new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDueno(cursor.getInt(3));
            listaMascotas.add(mascota);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i = 0 ; i<listaMascotas.size();i++){
            listaInformacion.add(listaMascotas.get(i).getIdMascota()+ "-"  + listaMascotas.get(i).getNombreMascota());
        }
    }
}
