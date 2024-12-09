package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AnalisisexitosoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisisexitoso); // Usa el layout correspondiente

        // Referencia al botón
        Button buttonResultados = findViewById(R.id.buttonResultados);

        // Setear un listener para que abra la actividad PerfilUsuario al hacer clic
        buttonResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la actividad PerfilUsuario
                Intent intent = new Intent(AnalisisexitosoActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent); // Abrir la actividad
            }
        });
    }
}
