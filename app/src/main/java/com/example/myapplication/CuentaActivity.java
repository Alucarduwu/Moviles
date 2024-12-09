package com.example.myapplication;  // Reemplaza con tu propio paquete

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CuentaActivity extends AppCompatActivity {

    private ImageButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        btnHome = findViewById(R.id.btnHome);

        // Botón de regresar
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> onBackPressed());  // Regresar a la actividad anterior

        // Botón "Cambiar de cuenta"
        ImageView btnLogout = findViewById(R.id.img_logout);
        btnLogout.setOnClickListener(v -> {
            // Mostrar ventana emergente de confirmación
            new AlertDialog.Builder(CuentaActivity.this)
                    .setMessage("¿Estás seguro que deseas cerrar sesión?")
                    .setCancelable(false)
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Si el usuario confirma, redirige a LoginActivity
                            Intent intent = new Intent(CuentaActivity.this, LoginActivity.class);
                            startActivity(intent);  // Redirige a la pantalla de login
                            finish(); // Cierra la actividad actual
                        }
                    })
                    .setNegativeButton("No", null) // Si el usuario cancela, no hace nada
                    .show();
        });

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(CuentaActivity.this, PerfilUsuarioActivity.class);
            startActivity(intent); // Inicia la actividad PerfilUsuarioActivity
        });

        // Aquí puedes agregar más listeners para otros botones si lo deseas
    }
}
