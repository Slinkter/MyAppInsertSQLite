package com.vidrieriachaloreyes.myappcrudsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vidrieriachaloreyes.myappcrudsqlite.Business.newUserActivity;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrar, btnConsultar, btnspinner, btnListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnspinner = findViewById(R.id.btnspinner);
        btnListView = findViewById(R.id.btnListView);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, newUserActivity.class));
            }
        });

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

    }
}
