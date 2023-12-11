package com.example.imhungry.gui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imhungry.R;

import java.io.IOException;

public class SingUpActivity extends AppCompatActivity {
    private Bitmap fotoPerfil;
    private Bitmap fotoCredencial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        Button buttonRegister = findViewById(R.id.button_register);
        Button buttonFotoPerfil = findViewById(R.id.button_profile_pic);
        Button buttonFotoCredencial = findViewById(R.id.button_crendential_pic);

        buttonRegister.setOnClickListener(v->{

            EditText etNombre = findViewById(R.id.text_view_nombre);
            EditText etMatricula = findViewById(R.id.text_view_matricula);
            EditText etApellidoP = findViewById(R.id.text_view_apellidoP);
            EditText etApellidoM = findViewById(R.id.text_view_apellidoM);
            EditText etCorreo = findViewById(R.id.text_view_correoInstitucional);
            EditText etPassword = findViewById(R.id.text_view_password_singin);
            String password = etPassword.getText().toString();
            String correo = etCorreo.getText().toString();
            String apellidoM = etApellidoM.getText().toString();
            String apellidoP = etApellidoP.getText().toString();
            String matricula = etMatricula.getText().toString();
            String nombre = etNombre.getText().toString();

        });

        ActivityResultLauncher<Intent> launcherImagenPerfil = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        if (data != null) {
                            Uri imagenSeleccionadaUri = data.getData();
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagenSeleccionadaUri);
                                fotoPerfil = bitmap;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        ActivityResultLauncher<Intent> launcherImagenCredencial = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri imagenSeleccionadaUri = data.getData();
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagenSeleccionadaUri);
                                fotoPerfil = bitmap;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        buttonFotoPerfil.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcherImagenPerfil.launch(intent);

        });

        buttonFotoCredencial.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcherImagenCredencial.launch(intent);

        });
    }
}