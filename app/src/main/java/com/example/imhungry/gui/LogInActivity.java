package com.example.imhungry.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imhungry.Domain.Estudiante;
import com.example.imhungry.Domain.EstudianteResponse;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class LogInActivity extends AppCompatActivity {

    Estudiante estudianteLogin;
    Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        TextView textViewRegistro = findViewById(R.id.button_sing_up);

         retrofit = new Retrofit.Builder()
                .baseUrl(API.getUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(v->{

                EditText usuarioET = findViewById(R.id.text_view_username);
                EditText passwordET = findViewById(R.id.text_view_password);

                String usuario = usuarioET.getText().toString();
                String password = passwordET.getText().toString();

            if(TextUtils.isEmpty(usuario)){

                mostrarToast("Debes ingresar un usuario");

            }else if(!validarCaracteres(usuario)) {

                mostrarToast("La matrícula contiene caracteres inválidos");

            }else if(TextUtils.isEmpty(password)){

                mostrarToast("Debes ingresar la contraseña");

            }else {
                estudianteLogin = new Estudiante(usuario, password);
                api();

            }
        });

        textViewRegistro.setOnClickListener(v->{

            Intent intent = new Intent(this, SingUpActivity.class);
            startActivity(intent);

        });
    }



    public void api(){

        ApiService apiService = retrofit.create(ApiService.class);
        Call<EstudianteResponse> call = apiService.estudiantesGetById(estudianteLogin.getMatricula());
        call.enqueue(new Callback<EstudianteResponse>() {
            @Override
            public void onResponse(Call<EstudianteResponse> call, Response<EstudianteResponse> response) {
                if((response.body().getEstudiante() == null)){
                    mostrarToast("La matrícula que has ingresado no se encuentra registrada");
                }else {
                    mostrarToast("Éxito");
                    iniciarSesion((response.body().getEstudiante()));
                }
            }
            @Override
            public void onFailure(Call<EstudianteResponse> call, Throwable t) {
                mostrarToast("Error al iniciar sesión");
            }
        });
    }

    public void iniciarSesion(Estudiante estudiante){

        String tipoPerfilComprador = estudiante.getTipoComprador();
        String tipoPerfilVendedor = estudiante.getTipoVendedor();

        if(tipoPerfilVendedor.equals("si") && tipoPerfilComprador.equals("si")){

            mostrarAlertaOpciones();

        }else if(tipoPerfilComprador.equals("si")){

            irAVentanaComprador();

        }else{

            irAVentanaVendedor();

        }
    }

    public void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public boolean validarCaracteres(String cadena) {
        return cadena != null && cadena.matches("[a-zA-Z0-9]+");
    }

    public void mostrarAlertaOpciones(){

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);

        builder.setTitle("Por favor elige una opción")
                .setMessage("¿Con qué tipo de perfil te gustaría iniciar sesión?");

        builder.setPositiveButton("Comprador", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                irAVentanaComprador();
            }
        });

        builder.setNegativeButton("Vendedor", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                irAVentanaVendedor();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void irAVentanaComprador(){
        Intent intent = new Intent(this, MainMenuCompradorActivity.class);
        startActivity(intent);
    }
    public void irAVentanaVendedor(){
        Intent intent = new Intent(this, MainMenuVendedorActivity.class);
        startActivity(intent);
    }
}