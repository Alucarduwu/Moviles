package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class HistorialClinicaActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextEdad, editTextSexo, editTextCorreo;
    private EditText editTextContacto1, editTextContacto2;
    private EditText editTextEnfermedadesPrevias, editTextAlergias, editTextCirugias, editTextMedicamentos, editTextVacunas;
    private EditText editTextPresionArterial, editTextFrecuenciaCardiaca, editTextTemperaturaCorporal, editTextPeso, editTextAltura;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historialclinica);

        // Inicializa Firebase
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        // Inicializa vistas
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextSexo = findViewById(R.id.editTextSexo);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextContacto1 = findViewById(R.id.editTextContacto1);
        editTextContacto2 = findViewById(R.id.editTextContacto2);
        editTextEnfermedadesPrevias = findViewById(R.id.editTextEnfermedadesPrevias);
        editTextAlergias = findViewById(R.id.editTextAlergias);
        editTextCirugias = findViewById(R.id.editTextCirugias);
        editTextMedicamentos = findViewById(R.id.editTextMedicamentos);
        editTextVacunas = findViewById(R.id.editTextVacunas);
        editTextPresionArterial = findViewById(R.id.editTextPresionArterial);
        editTextFrecuenciaCardiaca = findViewById(R.id.editTextFrecuenciaCardiaca);
        editTextTemperaturaCorporal = findViewById(R.id.editTextTemperaturaCorporal);
        editTextPeso = findViewById(R.id.editTextPeso);
        editTextAltura = findViewById(R.id.editTextAltura);

        // Cargar los datos del usuario desde Firebase
        cargarDatosUsuario();

        // Configura el botón para guardar datos
        Button buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonGuardar.setOnClickListener(v -> saveData());
    }

    private void cargarDatosUsuario() {
        // Obtener el usuario autenticado
        String userId = firebaseAuth.getCurrentUser().getUid();
        if (userId == null) {
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("users").document(userId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String nombre = documentSnapshot.getString("name");
                        String edad = documentSnapshot.getString("age");
                        String sexo = documentSnapshot.getString("gender");
                        String correo = firebaseAuth.getCurrentUser().getEmail();  // Obtenemos el correo desde el usuario autenticado

                        // Establecer los valores en los campos EditText
                        editTextNombre.setText(nombre);
                        editTextEdad.setText(edad);
                        editTextSexo.setText(sexo);
                        editTextCorreo.setText(correo);

                        // Configurar los campos como no editables
                        editTextNombre.setEnabled(false);
                        editTextEdad.setEnabled(false);
                        editTextSexo.setEnabled(false);
                        editTextCorreo.setEnabled(false);
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(HistorialClinicaActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show());
    }

    private void saveData() {
        // Obtener los valores ingresados por el usuario
        String contacto1 = editTextContacto1.getText().toString().trim();
        String contacto2 = editTextContacto2.getText().toString().trim();
        String enfermedadesPrevias = editTextEnfermedadesPrevias.getText().toString().trim();
        String alergias = editTextAlergias.getText().toString().trim();
        String cirugias = editTextCirugias.getText().toString().trim();
        String medicamentos = editTextMedicamentos.getText().toString().trim();
        String vacunas = editTextVacunas.getText().toString().trim();
        String presionArterial = editTextPresionArterial.getText().toString().trim();
        String frecuenciaCardiaca = editTextFrecuenciaCardiaca.getText().toString().trim();
        String temperaturaCorporal = editTextTemperaturaCorporal.getText().toString().trim();
        String peso = editTextPeso.getText().toString().trim();
        String altura = editTextAltura.getText().toString().trim();

        // Validar que los datos no estén vacíos (solo los campos nuevos)
        if (contacto1.isEmpty() || contacto2.isEmpty() || enfermedadesPrevias.isEmpty() || alergias.isEmpty()
                || cirugias.isEmpty() || medicamentos.isEmpty() || vacunas.isEmpty()
                || presionArterial.isEmpty() || frecuenciaCardiaca.isEmpty() || temperaturaCorporal.isEmpty()
                || peso.isEmpty() || altura.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un mapa con los datos adicionales
        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put("contacto1", contacto1);
        additionalData.put("contacto2", contacto2);
        additionalData.put("enfermedadesPrevias", enfermedadesPrevias);
        additionalData.put("alergias", alergias);
        additionalData.put("cirugias", cirugias);
        additionalData.put("medicamentos", medicamentos);
        additionalData.put("vacunas", vacunas);
        additionalData.put("presionArterial", presionArterial);
        additionalData.put("frecuenciaCardiaca", frecuenciaCardiaca);
        additionalData.put("temperaturaCorporal", temperaturaCorporal);
        additionalData.put("peso", peso);
        additionalData.put("altura", altura);

        // Obtener el usuario autenticado
        String userId = firebaseAuth.getCurrentUser().getUid();

        // Guardar los datos adicionales en Firestore
        db.collection("users").document(userId) // Usar el UID del usuario
                .set(additionalData, SetOptions.merge()) // Usamos 'merge' para no sobrescribir los datos existentes
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(HistorialClinicaActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Log.e("HistorialClinicaActivity", "Error al guardar datos: ", e);
                    Toast.makeText(HistorialClinicaActivity.this, "Error al guardar datos", Toast.LENGTH_LONG).show();
                });
    }
}

