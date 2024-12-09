package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContactosemergenciaActivity extends AppCompatActivity {

    private ImageButton btnRegresar;  // Botón para compartir y el de regresar


    RecyclerView recyclerView;
    ContactAdapter contactAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactosemergencia);


        btnRegresar = findViewById(R.id.btnRegresar); // Asegúrate de que este sea el ID correcto del botón de regresar

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa el adaptador con la lista de contactos
        contactAdapter = new ContactAdapter(getContacts());
        recyclerView.setAdapter(contactAdapter);

        btnRegresar.setOnClickListener(v -> regresarAperfil());

    }

    // Método que crea una lista de contactos con nombres y 3 iconos
    private List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();

        // Añadiendo tres contactos de ejemplo
        contacts.add(new Contact("Contacto 1", R.drawable.llamadatelefonica, R.drawable.whatsapp, R.drawable.mas));
        contacts.add(new Contact("Contacto 2", R.drawable.llamadatelefonica, R.drawable.whatsapp, R.drawable.mas));
        contacts.add(new Contact("Contacto 3", R.drawable.llamadatelefonica, R.drawable.whatsapp, R.drawable.mas));

        return contacts;
    }

    private void regresarAperfil() {
        // Crear el Intent para navegar a PerfilUsuarioActivity
        Intent intent = new Intent(ContactosemergenciaActivity.this, PerfilUsuarioActivity.class);
        startActivity(intent);
        // Si quieres que la actividad actual se cierre al regresar:
        finish();
    }


}
