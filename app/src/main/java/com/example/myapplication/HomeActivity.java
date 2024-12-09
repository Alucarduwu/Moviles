package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;  // Asegúrate de importar Handler correctamente

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);  // Se asume que esta es la pantalla de splash

        // Mostrar el logo por 3 segundos
        new Handler().postDelayed(() -> {
            // Después de 3 segundos, redirigimos a la pantalla de login
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);  // Usar HomeActivity aquí
            startActivity(intent);
            finish();  // Finaliza la actividad para que el usuario no pueda volver atrás
        }, 3000);  // 3000 milisegundos = 3 segundos
    }
}
