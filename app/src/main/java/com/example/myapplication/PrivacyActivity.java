package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacyActivity extends AppCompatActivity {
    private CheckBox acceptPrivacyCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        acceptPrivacyCheckBox = findViewById(R.id.acceptPrivacyCheckBox);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> proceedIfAccepted());
    }

    private void proceedIfAccepted() {
        if (acceptPrivacyCheckBox.isChecked()) {
            // Cambiar la actividad de destino a SplashActivity
            Intent intent = new Intent(PrivacyActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(PrivacyActivity.this, "Debe aceptar los términos de privacidad", Toast.LENGTH_SHORT).show();
        }
    }
}


