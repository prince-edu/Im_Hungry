package com.example.imhungry.ui.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imhungry.Domain.Estudiante;
import com.example.imhungry.Domain.Valoracion;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateReviewActivity extends AppCompatActivity {
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);

        retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();

        EditText editTextCalificacion = findViewById(R.id.calificacion_review);
        EditText editTextReview = findViewById(R.id.descripcion_review);
        Button buttonEnviarReview = findViewById(R.id.button_reviews);

        buttonEnviarReview.setOnClickListener(v -> {

            String calificacion = editTextCalificacion.getText().toString();
            String review = editTextReview.getText().toString();

            if(TextUtils.isEmpty(calificacion)){

                mostrarToast("No has ingresado una calificación.");

            }else if(!validarRango(calificacion)) {

                mostrarToast("La calificación debe ser del 1 al 10.");

            }else{

                int id = generarNumero();
                int calificacionNum = Integer.parseInt(calificacion);
                Valoracion valoracion = new Valoracion(id, review, calificacionNum);
                enviarReview(valoracion);
            }
        });


    }

    public void enviarReview (Valoracion valoracion){
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Valoracion> call = apiService.valoracionCreate(valoracion);
        call.enqueue(new Callback<Valoracion>() {
            @Override
            public void onResponse(@NonNull Call<Valoracion> call, @NonNull Response<Valoracion> response) {
                if(response.isSuccessful()){
                    mostrarToast("Registro éxitoso");
                    finish();
                }else{
                    mostrarToast("Ha ocurrido un error, inténtelo de nuevo más tarde");
                }
            }

            @Override
            public void onFailure(Call<Valoracion> call, Throwable t) {
                mostrarToast("Ha ocurrido un error, inténtelo de nuevo más tarde");

            }
        });
    }

    public void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
    public int generarNumero() {
            Random random = new Random();

            int numeroAleatorio = random.nextInt(900000) + 100000;

            return numeroAleatorio;
        }
    public static boolean validarRango(String numero) {
        String regex = "[1-9]|10";
        String numeroString = String.valueOf(numero);
        return numeroString.matches(regex);
    }
}