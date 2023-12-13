package com.example.imhungry.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

                Toast.makeText(LogInActivity.this, "Debes ingresar un usuario", Toast.LENGTH_SHORT).show();
//agregar validacion de caracteres.
            }else if(TextUtils.isEmpty(password)){

                Toast.makeText(LogInActivity.this, "Debes ingresar la contraseña", Toast.LENGTH_SHORT).show();

            }else {
                estudianteLogin = new Estudiante(usuario, password);
                Toast.makeText(this, estudianteLogin.getMatricula() + " " + estudianteLogin.getPassword(), Toast.LENGTH_SHORT).show();
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

                iniciarSesion((response.body().getEstudiante()));

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

        }else if(tipoPerfilComprador.equals("si")){
            Intent intent = new Intent(this, MainMenuCompradorActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, MainMenuVendedorActivity.class);
            startActivity(intent);
        }
    }

    public void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}