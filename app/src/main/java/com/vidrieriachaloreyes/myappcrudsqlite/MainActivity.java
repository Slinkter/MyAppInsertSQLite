package com.vidrieriachaloreyes.myappcrudsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vidrieriachaloreyes.myappcrudsqlite.Business.ConsultaActivity;
import com.vidrieriachaloreyes.myappcrudsqlite.Business.ConsultaComboActivity;
import com.vidrieriachaloreyes.myappcrudsqlite.Business.ConsultarListaViewActivity;
import com.vidrieriachaloreyes.myappcrudsqlite.Business.ListaMascotaActivity;
import com.vidrieriachaloreyes.myappcrudsqlite.Business.RegistrerActivity;
import com.vidrieriachaloreyes.myappcrudsqlite.Business.RegistroMascotaActivity;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;

import static com.vidrieriachaloreyes.myappcrudsqlite.Business.Utilidades.db_version;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrarU, btnConsultar, btnspinner, btnListView;

    Button btnRegistrarM, btnListaMascota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, db_version);

        btnRegistrarU = findViewById(R.id.btnRegistrarU);
        btnRegistrarM = findViewById(R.id.btnRegistrarM);
        btnConsultar = findViewById(R.id.btnConsultarMain);
        btnspinner = findViewById(R.id.btnSpinner);
        btnListView = findViewById(R.id.btnListView);
        btnListaMascota = findViewById(R.id.btnListaMascota);


        btnRegistrarU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, RegistrerActivity.class));
            }
        });

        btnRegistrarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistroMascotaActivity.class));
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConsultaActivity.class));
            }
        });

        btnspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConsultaComboActivity.class));
            }
        });

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConsultarListaViewActivity.class));
            }
        });

        btnListaMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListaMascotaActivity.class));
            }
        });


    }
}
