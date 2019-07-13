package com.vidrieriachaloreyes.myappcrudsqlite.Business;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.vidrieriachaloreyes.myappcrudsqlite.R;
import com.vidrieriachaloreyes.myappcrudsqlite.SQLite.Usuario;

public class DetalleUsuarioActivity extends AppCompatActivity {

    TextView campoId, campoNombre, campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        campoId = findViewById(R.id.campoIdD);
        campoNombre = findViewById(R.id.campoNombreD);
        campoTelefono = findViewById(R.id.campoTelefonoD);

        Bundle objetoEnviado = getIntent().getExtras();
        Usuario user = null;

        if (objetoEnviado!=null){
            user = (Usuario) objetoEnviado.getSerializable("usuario");
            campoId.setText(user.getId().toString());
            campoNombre.setText(user.getNombre().toString());
            campoTelefono.setText(user.getTelefono().toString());
        }


    }
}
