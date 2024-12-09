package com.example.myapplication;

import static com.example.myapplication.R.id.emailEditText;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicialización de vistas
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        mAuth = FirebaseAuth.getInstance();

        // Configurar botón "Siguiente"
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> loginUser());

        // Configurar enlace "Crear una cuenta"
        TextView createAccountTextView = findViewById(R.id.createAccountTextView);
        createAccountTextView.setOnClickListener(v -> navigateToRegisterActivity());
    }

    private void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Validar campos vacíos
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su correo y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar formato del correo electrónico
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Iniciar sesión con Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Redirigir al usuario a PerfilUsuarioActivity después del login exitoso
                        navigateToPerfilUsuarioActivity();
                    } else {
                        // Mostrar mensaje de error en caso de falla
                        Toast.makeText(this, "Error de autenticación. Verifique sus credenciales.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void navigateToPerfilUsuarioActivity() {
        // Redirigir al usuario a PerfilUsuarioActivity
        Intent intent = new Intent(LoginActivity.this, PerfilUsuarioActivity.class);
        startActivity(intent);
        //finish();
    }

    private void navigateToRegisterActivity() {
        // Navegar a RegisterActivity
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}


