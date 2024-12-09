package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class PerfilUsuarioActivity extends AppCompatActivity {

    private Button btnCodigoQR, btnContactosEmergencia;
    private ImageButton  btnMenu, btnHome, btnHistoriaClinica, btnSettings, btnPerfil, btnNotificaciones;
    private DrawerLayout drawerLayout;  // El contenedor del DrawerLayout
    private LinearLayout drawerMenu;    // El menú deslizable (ahora un LinearLayout)
    private LinearLayout btnGlucosa, btnOxigeno, btnSignosVitales, optionPreferencias, optionIdioma, optionHistoriaClinica, optionGuia;
    private TextView saludoTextView;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        try {
            // Inicialización de vistas
            saludoTextView = findViewById(R.id.saludoTextView);  // Saludo donde se clickea
            btnHome = findViewById(R.id.btnHome);
            btnHistoriaClinica = findViewById(R.id.btnHistoriaClinica);
            btnSettings = findViewById(R.id.btnSettings);
            btnPerfil = findViewById(R.id.btnPerfil);
            btnNotificaciones = findViewById(R.id.btnNotificaciones);
            // Inicialización de botones
            btnGlucosa =findViewById(R.id.btnGlucosa);
            btnOxigeno = findViewById(R.id.btnOxigeno);
            btnSignosVitales = findViewById(R.id.btnSignosVitales);
            btnCodigoQR = findViewById(R.id.btnCodigoQR);
            btnContactosEmergencia = findViewById(R.id.btnContactosEmergencia);
            btnHome = findViewById(R.id.btnHome);
            btnHistoriaClinica = findViewById(R.id.btnHistoriaClinica);
            btnSettings = findViewById(R.id.btnSettings);
            btnPerfil = findViewById(R.id.btnPerfil);
            btnNotificaciones = findViewById(R.id.btnNotificaciones);  // Notificaciones

            // Inicializar el DrawerLayout y el LinearLayout del menú deslizable
            drawerLayout = findViewById(R.id.drawerLayout);  // Contenedor
            drawerMenu = findViewById(R.id.drawerMenu);     // Menú deslizable

            // Inicializar el botón del menú
            btnMenu = findViewById(R.id.btnMenu);

            // Inicializar las opciones del menú deslizable
            optionPreferencias = findViewById(R.id.optionPreferencias);
            optionIdioma = findViewById(R.id.optionIdioma);
            optionHistoriaClinica = findViewById(R.id.optionHistoriaClinica);
            optionGuia = findViewById(R.id.optionGuia);

            // Acción del botón del menú para abrir/cerrar el menú deslizable
            btnMenu.setOnClickListener(v -> toggleDrawerMenu());

            // Acción del botón de notificaciones
            btnNotificaciones.setOnClickListener(v -> showToast("Notificaciones"));

            // Configuración de las opciones del menú deslizable
            setupMenuOptions();

            // Asignar los onClickListeners para los botones
            setButtonListeners();
            displayUserName();

            // Configurar el listener del saludoTextView
            saludoTextView.setOnClickListener(v -> mostrarDialogoOpciones()); // Mostrar el diálogo de opciones cuando se haga clic en el saludo


        } catch (Exception e) {
            // Si ocurre un error, lo mostramos en un Toast
            Toast.makeText(PerfilUsuarioActivity.this, "Error al inicializar la actividad: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // Método para alternar la visibilidad del menú deslizable
    private void toggleDrawerMenu() {
        try {
            Log.d("PerfilUsuarioActivity", "btnMenu clickeado");
            if (drawerLayout.isDrawerOpen(drawerMenu)) {
                drawerLayout.closeDrawer(drawerMenu); // Cerrar el menú
            } else {
                drawerLayout.openDrawer(drawerMenu);  // Abrir el menú
            }
        } catch (Exception e) {
            // Mostrar error si ocurre alguna excepción
            Toast.makeText(PerfilUsuarioActivity.this, "Error al abrir/cerrar el menú: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // Configuración de las opciones del menú deslizable
    private void setupMenuOptions() {
        try {
            // Acciones al seleccionar las opciones del menú
            optionPreferencias.setOnClickListener(v -> {
                Toast.makeText(PerfilUsuarioActivity.this, "Seleccionaste Preferencias y valores", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(drawerMenu);  // Cerrar el menú
            });

            optionIdioma.setOnClickListener(v -> {
                Toast.makeText(PerfilUsuarioActivity.this, "Seleccionaste Idioma", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(drawerMenu);  // Cerrar el menú
            });

            optionHistoriaClinica.setOnClickListener(v -> {
                Intent intent = new Intent(PerfilUsuarioActivity.this, HistorialClinicaActivity.class);
                startActivity(intent); // Redirige a la actividad HistorialClinicaActivity
                drawerLayout.closeDrawer(drawerMenu);  // Cerrar el menú
            });


            optionGuia.setOnClickListener(v -> {
                Toast.makeText(PerfilUsuarioActivity.this, "Seleccionaste Guía de uso", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(drawerMenu);  // Cerrar el menú
            });
        } catch (Exception e) {
            // Mostrar error si ocurre alguna excepción
            Toast.makeText(PerfilUsuarioActivity.this, "Error al configurar el menú: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    // Método para asignar las acciones a los botones
    private void setButtonListeners() {
        try {
            btnHome.setOnClickListener(v -> showToast("Ir a la página principal"));
            btnHistoriaClinica.setOnClickListener(v -> {
                Intent intent = new Intent(PerfilUsuarioActivity.this, HistorialClinicaActivity.class);
                startActivity(intent); // Redirige a la actividad HistorialClinicaActivity
            });

            btnSettings.setOnClickListener(v -> showToast("Ir a Configuración"));
            btnPerfil.setOnClickListener(v -> showToast("Perfil de usuario"));
        } catch (Exception e) {
            Toast.makeText(PerfilUsuarioActivity.this, "Error al asignar los listeners: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        try {
            // Botón de Glucosa
            btnGlucosa.setOnClickListener(v -> {
                Intent intent = new Intent(PerfilUsuarioActivity.this, GlucosascActivity.class);
                startActivity(intent); // Inicia la actividad GlucosascActivity
            });

            // Botón de Oxigeno
            btnOxigeno.setOnClickListener(v -> {
                Intent intent = new Intent(PerfilUsuarioActivity.this, OxigenoscActivity.class);
                startActivity(intent); // Inicia la actividad GlucosascActivity
            });

            // Botón de Oxigeno
            btnSignosVitales.setOnClickListener(v -> {
                Intent intent = new Intent(PerfilUsuarioActivity.this, SignosvitalesscActivity.class);
                startActivity(intent); // Inicia la actividad GlucosascActivity
            });

            btnCodigoQR.setOnClickListener(v -> {
                Intent intent = new Intent(PerfilUsuarioActivity.this, CodigoqrActivity.class);
                startActivity(intent); // Inicia la actividad GlucosascActivity
            });

            btnContactosEmergencia.setOnClickListener(v -> {
                Intent intent = new Intent(PerfilUsuarioActivity.this, ContactosemergenciaActivity.class);
                startActivity(intent); // Inicia la actividad GlucosascActivity
            });

            btnPerfil.setOnClickListener(v -> {
                Intent intent = new Intent(PerfilUsuarioActivity.this, CuentaActivity.class);
                startActivity(intent); // Inicia la actividad GlucosascActivity
            });

            // Botones de la barra inferior
            btnHome.setOnClickListener(v -> showToast("Ir a la página principal"));
            btnHistoriaClinica.setOnClickListener(v -> {
                // Crear un Intent para abrir HistorialClinicaActivity
                Intent intent = new Intent(PerfilUsuarioActivity.this, HistorialClinicaActivity.class);
                startActivity(intent); // Inicia la actividad
            });
            btnSettings.setOnClickListener(v -> showToast("Ir a Configuración"));
        } catch (Exception e) {
            // Mostrar error si ocurre alguna excepción
            Toast.makeText(PerfilUsuarioActivity.this, "Error al asignar los listeners: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    private void displayUserName() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("users").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("name");
                        if (name != null && !name.isEmpty()) {
                            saludoTextView.setText("Hola de nuevo, " + name);
                        } else {
                            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            saludoTextView.setText("Hola de nuevo, " + email);
                        }
                    } else {
                        Log.e("PerfilUsuarioActivity", "El documento del usuario no existe");
                        Toast.makeText(PerfilUsuarioActivity.this, "No se encontró el usuario en Firestore", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("PerfilUsuarioActivity", "Error al obtener el nombre del usuario: ", e);
                    Toast.makeText(PerfilUsuarioActivity.this, "Error al obtener el nombre", Toast.LENGTH_SHORT).show();
                });
    }

    private void mostrarDialogoOpciones() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_opciones_usuario);  // Asegúrate de que este archivo XML exista
        dialog.setCancelable(true);  // Permite que el diálogo se cierre tocando fuera de él

        // Referencias a los elementos del diálogo
        TextView tvUsuario = dialog.findViewById(R.id.tvUsuario);
        LinearLayout llSobreNosotros = dialog.findViewById(R.id.llSobreNosotros);
        LinearLayout llContactanos = dialog.findViewById(R.id.llContactanos);
        LinearLayout llTerminos = dialog.findViewById(R.id.llTerminos);
        LinearLayout llComentarios = dialog.findViewById(R.id.llComentarios);

        // Referencia al botón de cierre (X)
        ImageButton btnCloseDialog = dialog.findViewById(R.id.btnCloseDialog);

        // Establecer el texto del usuario
        tvUsuario.setText("Usuario: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());

        // Configurar el listener para cerrar el diálogo cuando se presiona la "X"
        btnCloseDialog.setOnClickListener(v -> dialog.dismiss());

        // Configurar los listeners de las opciones
        llSobreNosotros.setOnClickListener(v -> {
            navigateToFragment(new SobreNosotrosFragment());
            dialog.dismiss();
        });

        llContactanos.setOnClickListener(v -> {
            navigateToFragment(new ContactanosFragment());
            dialog.dismiss();
        });

        llTerminos.setOnClickListener(v -> {
            navigateToFragment(new TerminosFragment());
            dialog.dismiss();
        });

        llComentarios.setOnClickListener(v -> {
            navigateToFragment(new ComentariosFragment());
            dialog.dismiss();
        });

        // Mostrar el diálogo
        dialog.show();
    }

    private void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawerLayout, fragment)  // Usando el ID de tu contenedor ya existente (drawerLayout por ejemplo)
                .addToBackStack(null)
                .commit();
    }
    // Método para mostrar mensajes de Toast de forma centralizada
    private void showToast(String message) {
        Toast.makeText(PerfilUsuarioActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

