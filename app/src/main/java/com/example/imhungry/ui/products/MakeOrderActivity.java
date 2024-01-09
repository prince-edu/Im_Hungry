package com.example.imhungry.ui.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imhungry.Domain.Pedido;
import com.example.imhungry.Domain.Venta;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;
import com.example.imhungry.databinding.ActivityMakeOrderBinding;

import java.security.SecureRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MakeOrderActivity extends AppCompatActivity {
    private ActivityMakeOrderBinding binding;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        binding = ActivityMakeOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();

        Button botonPedido = findViewById(R.id.button_pedido);
        EditText editTextCantidad = findViewById(R.id.cantidad_productos_pedido);
        EditText editTextPreferencias = findViewById(R.id.preferencias_pedido);

        Intent intent = getIntent();
        Pedido pedido = (Pedido) intent.getSerializableExtra("pedido");

        botonPedido.setOnClickListener(v->{

            String cantidadString = editTextCantidad.getText().toString();
            if(TextUtils.isEmpty(cantidadString)){
                mostrarToast("Debes ingresar una cantidad.");
            }else {
                int cantidad = Integer.parseInt(cantidadString);
                double precio = pedido.getPrecioTotal();
                double precioFinal = precio * cantidad;

                String preferencias = editTextPreferencias.getText().toString();
                pedido.setPreferencias(preferencias + " Cantidad de unidades deseadas: " + cantidad);
                pedido.setPrecioTotal(precioFinal);

                String id = randomIDNumerico(6);
                int idVenta = Integer.parseInt(id);
                Venta venta = new Venta(idVenta, cantidad, pedido.getFechaPedido(), precioFinal);
                pedido.setId_venta(idVenta);

                crearVenta(venta);
                realizarPedido(pedido);
            }
        });
    }

    public void crearVenta (Venta venta){
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Venta> call = apiService.ventaCreate(venta);
        call.enqueue(new Callback<Venta>() {
            @Override
            public void onResponse(@NonNull Call<Venta> call, @NonNull Response<Venta> response) {
                if(response.isSuccessful()){
                    mostrarToast("Venta registrada.");
                    finish();
                }else{
                    mostrarToast("Ha ocurrido un error, inténtelo de nuevo más tarde00");
                }
            }

            @Override
            public void onFailure(Call<Venta> call, Throwable t) {
                mostrarToast("Ha ocurrido un error, inténtelo de nuevo más tardee");

            }
        });
    }

    public void realizarPedido (Pedido pedido){
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Pedido> call = apiService.pedidosCreate(pedido);
        call.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(@NonNull Call<Pedido> call, @NonNull Response<Pedido> response) {
                if(response.isSuccessful()){
                    mostrarToast("Pedido realizado con éxito.");
                    finish();
                }else{
                    mostrarToast("Ha ocurrido un error, inténtelo de nuevo más tardee");
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                mostrarToast("Ha ocurrido un error, inténtelo de nuevo más tardeee");

            }
        });
    }

    public void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public static String randomIDNumerico(int length) {
        String CHARACTERS = "0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}