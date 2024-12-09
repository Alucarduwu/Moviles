package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConditionsActivity extends AppCompatActivity {
    private CheckBox hypertensionCheckBox, arthritisCheckBox, diabetesCheckBox, heartCheckBox, otherCheckBox;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "UserPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);

        // Inicializar las CheckBoxes
        hypertensionCheckBox = findViewById(R.id.hypertensionCheckBox);
        arthritisCheckBox = findViewById(R.id.arthritisCheckBox);
        diabetesCheckBox = findViewById(R.id.diabetesCheckBox);
        heartCheckBox = findViewById(R.id.heartCheckBox);
        otherCheckBox = findViewById(R.id.otherCheckBox);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Configurar el botón para guardar y proceder
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> saveConditionsAndProceed());
    }

    private void saveConditionsAndProceed() {
        // Crear una lista de condiciones seleccionadas
        List<String> conditions = new ArrayList<>();
        if (hypertensionCheckBox.isChecked()) conditions.add("Hipertensión");
        if (arthritisCheckBox.isChecked()) conditions.add("Artritis");
        if (diabetesCheckBox.isChecked()) conditions.add("Diabetes");
        if (heartCheckBox.isChecked()) conditions.add("Corazón");
        if (otherCheckBox.isChecked()) conditions.add("Otros");

        // Verificar si hay condiciones seleccionadas
        if (conditions.isEmpty()) {
            Toast.makeText(ConditionsActivity.this, "Por favor, seleccione al menos una condición", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar las condiciones seleccionadas en SharedPreferences
        Set<String> conditionsSet = new HashSet<>(conditions); // Convertir la lista a un Set
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("selected_conditions", conditionsSet); // Guardar como Set de Strings
        editor.apply(); // Aplicar los cambios

        // Guardar las condiciones seleccionadas en Firestore sin sobrescribir los datos existentes
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            // Crear un mapa con las condiciones seleccionadas
            Map<String, Object> conditionsData = Map.of("conditions", conditions);

            // Intentar actualizar las condiciones en Firestore sin sobrescribir los demás datos
            db.collection("users").document(currentUser.getUid())
                    .update(conditionsData) // Usamos update() para no sobrescribir los datos existentes
                    .addOnSuccessListener(aVoid -> {
                        // Si la actualización fue exitosa, pasar a la siguiente pantalla
                        Intent intent = new Intent(ConditionsActivity.this, PrivacyActivity.class);
                        startActivity(intent);
                        finish(); // Finalizar esta actividad para no volver atrás
                    })
                    .addOnFailureListener(e -> {
                        // Si ocurrió un error al guardar, mostrar un mensaje
                        Toast.makeText(ConditionsActivity.this, "Error al guardar condiciones: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        } else {
            // Si el usuario no está autenticado, mostrar un mensaje de error
            Toast.makeText(ConditionsActivity.this, "Error: Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }
}