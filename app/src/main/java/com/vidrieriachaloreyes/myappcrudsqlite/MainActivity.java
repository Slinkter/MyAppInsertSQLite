package com.vidrieriachaloreyes.myappcrudsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vidrieriachaloreyes.myappcrudsqlite.Business.ConsultaActivity;
import com.vidrieriachaloreyes.myappcrudsqlite.Business.RegistrerActivity;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrar, btnConsultar, btnspinner, btnListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnConsultar = findViewById(R.id.btnConsultarMain);
        btnspinner = findViewById(R.id.btnspinner);
        btnListView = findViewById(R.id.btnListView);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, RegistrerActivity.class));
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConsultaActivity.class));
            }
        });


    }
}
