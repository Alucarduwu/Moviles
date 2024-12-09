package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CodigoqrActivity extends AppCompatActivity {

    private ImageButton btnCompartir, btnRegresar;  // Botón para compartir y el de regresar
    private ImageView imagenHistorialClinico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigoqr); // Asegúrate de que este sea el layout correcto

        btnCompartir = findViewById(R.id.btnCompartir);
        btnRegresar = findViewById(R.id.btnRegresar); // Asegúrate de que este sea el ID correcto del botón de regresar
        imagenHistorialClinico = findViewById(R.id.imagenHistorialClinico);

        // Configura el evento del botón de compartir
        btnCompartir.setOnClickListener(v -> compartirQR());

        // Configura el evento del botón de regresar
        btnRegresar.setOnClickListener(v -> regresarAperfil());
    }

    private void compartirQR() {
        // Obtén la imagen del QR como Bitmap
        Bitmap bitmap = getBitmapFromImageView(imagenHistorialClinico);

        if (bitmap != null) {
            try {
                // Guarda la imagen en un archivo temporal
                File file = new File(getExternalCacheDir(), "qr_code_image.png");
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();

                // Crea un URI del archivo para compartirlo
                Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);

                // Crea un Intent de compartir
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/png");
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Mira este código QR con mi historial clínico.");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                // Inicia el Intent
                startActivity(Intent.createChooser(shareIntent, "Compartir código QR"));
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al compartir el QR", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Bitmap getBitmapFromImageView(ImageView imageView) {
        // Convierte la imagen del ImageView en un Bitmap
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        return bitmap;
    }

    // Método para regresar a la actividad de PerfilUsuario
    private void regresarAperfil() {
        // Crear el Intent para navegar a PerfilUsuarioActivity
        Intent intent = new Intent(CodigoqrActivity.this, PerfilUsuarioActivity.class);
        startActivity(intent);
        // Si quieres que la actividad actual se cierre al regresar:
        finish();
    }
}
