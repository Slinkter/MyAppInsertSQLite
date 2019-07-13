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
import android.widget.Toast;

import com.vidrieriachaloreyes.myappcrudsqlite.R;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.Usuario;

import java.util.ArrayList;

public class ConsultarListaViewActivity extends AppCompatActivity {

    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuario;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_view);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);


        listViewPersonas = findViewById(R.id.listViewPersonas);

        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "Id : " + listaUsuario.get(position).getId() + "\n";
                informacion += "Nombre : " + listaUsuario.get(position).getNombre() + "\n";
                informacion += "Telefono : " + listaUsuario.get(position).getTelefono() + "\n";

                Toast.makeText(ConsultarListaViewActivity.this, informacion, Toast.LENGTH_SHORT).show();
                Usuario user = listaUsuario.get(position);
                Intent intent = new Intent(ConsultarListaViewActivity.this, DetalleUsuarioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", user);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario usuario = null;
        listaUsuario = new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()) {
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            listaUsuario.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i < listaUsuario.size(); i++) {
            listaInformacion.add(listaUsuario.get(i).getId() + " - " + listaUsuario.get(i).getNombre());
        }
    }
}
