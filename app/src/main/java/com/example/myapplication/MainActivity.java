package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private int clickCount = 0;  // Contador de clics

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener el ConstraintLayout raíz donde se harán los clics
        ConstraintLayout mainLayout = findViewById(R.id.main);

        // Establecer un OnClickListener para el layout
        mainLayout.setOnClickListener(v -> onLayoutClick());
    }

    private void onLayoutClick() {
        clickCount++;  // Incrementamos el contador de clics

        // Si se ha hecho 3 clics, redirigimos al login
        if (clickCount >= 3) {
            openLoginActivity();  // Abrir la actividad de login
            clickCount = 0;  // Resetear el contador
        } else {
            // Mostrar un Toast indicando cuántos clics faltan
            Toast.makeText(this, "Haz clic " + (3 - clickCount) + " más para acceder al login.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openLoginActivity() {
        // Abrir la actividad de login
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();  // Finaliza la actividad actual para evitar que el usuario vuelva atrás
    }
}



