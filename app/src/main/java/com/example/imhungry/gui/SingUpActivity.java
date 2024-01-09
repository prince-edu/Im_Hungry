package com.example.imhungry.gui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imhungry.Domain.Estudiante;
import com.example.imhungry.Domain.EstudianteResponse;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingUpActivity extends AppCompatActivity {
    private String fotoPerfil;
    private String fotoCredencial;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        Button buttonRegister = findViewById(R.id.button_register);
        Button buttonFotoPerfil = findViewById(R.id.button_profile_pic);
        Button buttonFotoCredencial = findViewById(R.id.button_crendential_pic);
        CheckBox cbVendedor = findViewById(R.id.checkboxVendedor);
        CheckBox cbComprador = findViewById(R.id.checkboxComprador);

        retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();

        buttonRegister.setOnClickListener(v-> {

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

            String vendedor = new String();//se ha inicializado este String ya que en la parte de abajo
            String comprador = new String();//no permite usar el TextUtils.isEmpty() si solo se declara;

            if (cbVendedor.isChecked()) {
                vendedor = "si";
            }
            if (cbComprador.isChecked()) {
                comprador = "si";
            }


            if(!validarMatricula(matricula)){
                mostrarToast("No has ingresado una matrícula o contiene caracteres inválidos");
            }else if(!validarNombre(nombre)){
                mostrarToast("No has ingresado un nombre o contiene caracteres inválidos");
            }else if(!validarNombre(apellidoP)){
                mostrarToast("No has ingresado un apellido paterno o contiene caracteres inválidos");
            }else if(!validarNombre(apellidoM)){
                mostrarToast("No has ingresado un apellido materno o contiene caracteres inválidos");
            }else if(!validarCorreo(correo)){
                mostrarToast("No has ingresado un correo institucional o contiene caracteres inválidos");
            }else if(TextUtils.isEmpty(password)){
                mostrarToast("No has ingresado una contraseña");
            }else if(TextUtils.isEmpty(fotoPerfil)){
                mostrarToast("No has agregado una foto de perfil");
            }else if(TextUtils.isEmpty(fotoCredencial)){
                mostrarToast("No has ingresado una foto de tu credencial");
            }else if(TextUtils.isEmpty(comprador) && TextUtils.isEmpty(vendedor)){
                mostrarToast("Debes elegir al menos un rol");
            }else{
                Estudiante estudiante = new Estudiante(matricula, nombre, apellidoP, apellidoM, correo, password, vendedor, comprador,
                fotoPerfil, fotoCredencial);
                buscarEstudiante(estudiante);

            }
        });

        ActivityResultLauncher<Intent> launcherImagenPerfil = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        if (data != null) {
                            Uri imagenSeleccionadaUri = data.getData();


                               /* Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagenSeleccionadaUri);
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream); // Comprimir el bitmap en formato PNG
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                String imagenString = new String(byteArray);
                                fotoPerfil = imagenString;
                                */
                               //fotoPerfil = Base64.encodeToString(byteArray, Base64.DEFAULT);
                                fotoPerfil = "android.graphics.Bitmap@25e32";




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

                                // Comprimir el bitmap en formato JPEG con calidad del 100%
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                                // Obtener el arreglo de bytes a partir del flujo de salida
                                byte[] byteArray = byteArrayOutputStream.toByteArray();

                                // Convertir el arreglo de bytes a una cadena Base64
                                String imagenString = Base64.encodeToString(byteArray, Base64.DEFAULT);

                                // Aquí puedes utilizar la cadena 'imagenString' según tus necesidades
                                fotoCredencial = imagenString;

                                // Mostrar información de la imagen (puedes eliminar esta línea si no es necesaria)
                                mostrarToast("Foto convertida a cadena: " + fotoCredencial);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                                mostrarToast("Error: No se pudo encontrar la imagen seleccionada");
                            } catch (IOException e) {
                                e.printStackTrace();
                                mostrarToast("Error de lectura al procesar la imagen");
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

    public void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
    public boolean validarNombre(String nombre) {
        return nombre != null && nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$");
    }
    public boolean validarMatricula(String matricula) {
        return matricula != null && matricula.matches("[a-zA-Z0-9]+");
    }
    public boolean validarCorreo(String correo){
        return correo != null && correo.matches("^[a-zA-Z0-9]+@estudiantes\\.uv\\.mx$");
    }

    public void registrarEstudiante (Estudiante estudiante){
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Estudiante> call = apiService.estudiantesCreate(estudiante);
        call.enqueue(new Callback<Estudiante>() {
            @Override
            public void onResponse(@NonNull Call<Estudiante> call, @NonNull Response<Estudiante> response) {
                if(response.isSuccessful()){
                    mostrarToast("Registro éxitoso");
                    finish();
                }else{
                    mostrarToast("Ha ocurrido un error, inténtelo de nuevo más tardee");
                }
            }

            @Override
            public void onFailure(Call<Estudiante> call, Throwable t) {
                mostrarToast("Ha ocurrido un error, inténtelo de nuevo más tardeeee");

            }
        });
    }

    public void buscarEstudiante(Estudiante estudiante){

        ApiService apiService = retrofit.create(ApiService.class);
        Call<EstudianteResponse> call = apiService.estudiantesGetById(estudiante.getMatricula());
        call.enqueue(new Callback<EstudianteResponse>() {

            @Override
            public void onResponse(Call<EstudianteResponse> call, Response<EstudianteResponse> response) {
                if((response.body().getEstudiante() == null)){
                     registrarEstudiante(estudiante);
                }else {
                    mostrarToast("La matrícula que has ingresado ya se encuentra registrada");
                }
            }
            @Override
            public void onFailure(Call<EstudianteResponse> call, Throwable t) {
                mostrarToast("Ha ocurrido un error");
            }
        });
    }
}