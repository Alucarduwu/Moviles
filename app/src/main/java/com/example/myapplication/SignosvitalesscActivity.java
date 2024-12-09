package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;

public class SignosvitalesscActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signosvitalessc);

        progressBar = findViewById(R.id.progressBar);

        // Simular la carga durante 5 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Después de 5 segundos, pasar a la actividad principal
                Intent intent = new Intent(SignosvitalesscActivity.this, AnalisisexitosoActivity.class);
                startActivity(intent);
                finish(); // Termina esta actividad para que no regrese al presionar "atrás"
            }
        }, 5000); // 5000 milisegundos = 5 segundos

        // Simular el avance de la barra de progreso
        final int progressInterval = 50; // Intervalo de tiempo entre cada avance (en milisegundos)
        final int maxProgress = 100; // Valor máximo de la barra de progreso
        final int step = 1; // Cuánto aumenta el progreso en cada intervalo

        final Handler progressHandler = new Handler();
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                int progress = progressBar.getProgress();
                if (progress < maxProgress) {
                    progressBar.setProgress(progress + step);
                    progressHandler.postDelayed(this, progressInterval);
                }
            }
        };
        progressHandler.post(progressRunnable);
    }
}
