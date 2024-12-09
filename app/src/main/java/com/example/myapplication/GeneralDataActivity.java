package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GeneralDataActivity extends AppCompatActivity {
    private EditText nameEditText, ageEditText;
    private Spinner genderSpinner;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_data);

        // Inicializa Firebase
        db = FirebaseFirestore.getInstance();

        // Inicializa vistas
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        genderSpinner = findViewById(R.id.genderSpinner);

        // Configura el Spinner con un ArrayAdapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        // Configura el botón "Siguiente"
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> saveDataAndProceed());
    }

    private void saveDataAndProceed() {
        // Obtén los valores ingresados por el usuario
        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String gender = genderSpinner.getSelectedItem().toString();

        // Validaciones básicas
        if (name.isEmpty() || age.isEmpty() || gender.equals("Seleccionar género")) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que la edad sea un número
        try {
            Integer.parseInt(age);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingresa una edad válida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un mapa con los datos del usuario
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("age", age);
        userData.put("gender", gender);

        // Obtén el usuario autenticado
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Guarda o actualiza los datos en Firestore
        db.collection("users").document(userId) // Usar el UID del usuario como identificador
                .update(userData) // Usamos update para no sobrescribir los datos anteriores
                .addOnSuccessListener(aVoid -> {
                    // Si los datos se guardaron correctamente, avanza a la siguiente pantalla
                    Intent intent = new Intent(GeneralDataActivity.this, ConditionsActivity.class);
                    startActivity(intent);
                    finish(); // Termina la actividad actual
                })
                .addOnFailureListener(e -> {
                    Log.e("GeneralDataActivity", "Error al guardar datos: ", e);
                    Toast.makeText(this, "Error al guardar datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}
