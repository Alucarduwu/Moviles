package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private int clickCount = 0; // Contador de clics
    private ImageView fingerprintIcon; // Icono de huella
    private TextView pressCountText; // Texto para mostrar el número de clics

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Buscar los elementos de la interfaz
        fingerprintIcon = findViewById(R.id.fingerprintIcon);
        pressCountText = findViewById(R.id.pressCountText);

        // Inicializar el texto con el número correcto de clics restantes
        pressCountText.setText("Presione con la huella que desea registrar: 3 veces");

        // Configurar el listener para el icono de huella
        fingerprintIcon.setOnClickListener(v -> handleFingerprintClick());
    }

    private void handleFingerprintClick() {
        // Incrementa el contador de clics
        clickCount++;

        // Actualiza el texto debajo del icono con el número de clics restantes
        pressCountText.setText("Presione con la huella que desea registrar: " + (3 - clickCount) + " veces");

        // Cambiar el color del icono según el número de clics
        switch (clickCount) {
            case 1:
                fingerprintIcon.setColorFilter(getResources().getColor(android.R.color.holo_blue_light)); // Azul claro
                break;
            case 2:
                fingerprintIcon.setColorFilter(getResources().getColor(android.R.color.holo_blue_dark)); // Azul más oscuro
                break;
            case 3:
                fingerprintIcon.setColorFilter(getResources().getColor(android.R.color.holo_blue_bright)); // Azul completo
                // Redirigir a LoginActivity después del tercer clic
                proceedToLogin();
                break;
            default:
                break;
        }
    }

    private void proceedToLogin() {
        // Redirige a LoginActivity
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();  // Finaliza SplashActivity para evitar que el usuario regrese
    }
}
