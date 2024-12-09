package com.example.myapplication;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;

public class FingerprintActivity extends AppCompatActivity {
    private int clickCount = 0;
    private Button fingerprintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);

        fingerprintButton = findViewById(R.id.fingerprintButton);

        fingerprintButton.setOnClickListener(v -> handleFingerprintClick());
    }

    private void handleFingerprintClick() {
        clickCount++;
        if (clickCount == 3) {
            proceedToHome();
        } else {
            Toast.makeText(FingerprintActivity.this, "Haz clic " + (3 - clickCount) + " más veces", Toast.LENGTH_SHORT).show();
        }
    }

    private void proceedToHome() {
        Intent intent = new Intent(FingerprintActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

