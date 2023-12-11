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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        TextView textViewRegistro = findViewById(R.id.button_sing_up);

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(v->{

                EditText usuarioET = findViewById(R.id.text_view_username);
                EditText passwordET = findViewById(R.id.text_view_password);

                String usuario = usuarioET.getText().toString();
                String password = passwordET.getText().toString();



            if(TextUtils.isEmpty(usuario)){

                Toast.makeText(LogInActivity.this, "Debes ingresar un usuario", Toast.LENGTH_SHORT).show();

            }else if(TextUtils.isEmpty(password)){

                Toast.makeText(LogInActivity.this, "Debes ingresar la contraseña", Toast.LENGTH_SHORT).show();

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

         final Estudiante usuario2 = new Estudiante();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((MoshiConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Estudiante> call = apiService.estudiantesGetById(estudianteLogin.getMatricula());
        call.enqueue(new Callback<Estudiante>() {
            @Override
            public void onResponse(Call<Estudiante> call, Response<Estudiante> response) {
                if(response.isSuccessful()){

                    Estudiante usuario = response.body();
                    Log.d("A", "onResponse: "+response.code());
                    Log.d("A", "onResponse: "+(response.body()));
                    Log.d("A", "onResponse: "+usuario.getMatricula());
                    Log.d("A", "onResponse: "+usuario.getNombre());
                    Log.d("A", "onResponse: "+usuario.getPassword());

                   // usuario2.setMatricula(usuario.getMatricula());

                    //iniciarSesion(usuario);

                }else{
                    Toast.makeText(LogInActivity.this, "El usuario que has ingresado no existe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Estudiante> call, Throwable t) {
                Toast.makeText(LogInActivity.this, "Error al iniciar sesión " + usuario2.getMatricula() + " " + usuario2.getPassword(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void iniciarSesion(@NonNull Estudiante estudiante){

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
}