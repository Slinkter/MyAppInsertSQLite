package com.vidrieriachaloreyes.myappcrudsqlite.Business;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.vidrieriachaloreyes.myappcrudsqlite.R;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.Mascota;

import org.w3c.dom.Text;

import static com.vidrieriachaloreyes.myappcrudsqlite.Business.Utilidades.db_version;

public class DetalleMascotaActivity extends AppCompatActivity {

    ConexionSQLiteHelper conn;
    TextView campoIdMascota, campoNombreMascota, campoRaza;
    TextView campoIdPersona, campoNombrePersona, campoTelefonoPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, db_version);

        campoIdPersona = findViewById(R.id.idMascotaDetallePersona);
        campoNombrePersona = findViewById(R.id.idMascotaDetallePersonaNombre);
        campoTelefonoPersona = findViewById(R.id.idMascotaDetallePersonaTelefono);

        campoIdMascota = findViewById(R.id.idMascotaDetalle);
        campoNombreMascota = findViewById(R.id.idMascotaNombreDetalle);
        campoRaza = findViewById(R.id.idMascotaRazaDetalle);

        Bundle objetoEnviado = getIntent().getExtras();
        Mascota mascota = null;

        if (objetoEnviado != null) {
            mascota = (Mascota) objetoEnviado.getSerializable("mascota");
            campoIdMascota.setText(mascota.getIdMascota().toString());
            campoNombreMascota.setText(mascota.getNombreMascota().toString());
            campoRaza.setText(mascota.getRaza().toString());
            consultarPersona(mascota.getIdDueno());
        }


    }

    private void consultarPersona(Integer idPersona) {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {idPersona.toString()};
        String[] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};

        Toast.makeText(getApplicationContext(), "El documento" + idPersona, Toast.LENGTH_SHORT).show();
        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_ID + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            campoIdPersona.setText(idPersona.toString());
            campoNombrePersona.setText(cursor.getString(0));
            campoTelefonoPersona.setText(cursor.getString(1));
            cursor.close();

        } catch (Exception e) {
            Toast.makeText(this, "El documento no existe ", Toast.LENGTH_SHORT).show();
            campoNombrePersona.setText(" ");
            campoTelefonoPersona.setText(" ");
        }


    }
}
