package com.example.imhungry.ui.products;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.imhungry.Domain.Pedido;
import com.example.imhungry.Domain.Producto;
import com.example.imhungry.Domain.ProductoResponse;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;
import com.example.imhungry.databinding.ActivityViewProductBinding;
import com.example.imhungry.gui.LogInActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.security.SecureRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ViewProductActivity extends AppCompatActivity {
    private ActivityViewProductBinding binding;
    Retrofit retrofit;
    int idproducto;
    Double precioProdcuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        binding = ActivityViewProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();

        Button botonReview = findViewById(R.id.button_reviews);
        Button botonHacerPedido = findViewById(R.id.button_make_order);

        TextView textViewTitulo = findViewById(R.id.textView_titulo);
        TextView textViewTitulo2 = findViewById(R.id.textView3);
        ImageView imageViewProducto = findViewById(R.id.image_view_producto);
        TextView textViewDescripcion = findViewById(R.id.descripcion_producto);
        TextView textViewPrecio = findViewById(R.id.precio_producto);
        TextView textViewCantidad = findViewById(R.id.cantidad_producto);
        TextView textViewHorario = findViewById(R.id.horario_producto);
        TextView textViewPuntoEncuentro = findViewById(R.id.punto_encuentro);

        if (getIntent().hasExtra("nombre")) {
            String nombreProducto = getIntent().getStringExtra("nombre");
            buscarProducto(nombreProducto);
        }

        botonReview.setOnClickListener(v->{

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(this);

                builder.setTitle("¿Qué deseas hacer?")
                        .setMessage("Selecciona una opción");

                builder.setPositiveButton("ver reviews del producto", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(botonHacerPedido.getContext(), ReviewsActivity.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("crear review", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(botonHacerPedido.getContext(), CreateReviewActivity.class);
                        startActivity(intent);
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        });

        botonHacerPedido.setOnClickListener(v->{
            int idPedido = Integer.parseInt(randomIDNumerico(5));

            String fecha = new Date().toString();


            Pedido pedido = new Pedido(idPedido, "", fecha, precioProdcuto, "Pendiente", 0,
                    LogInActivity.estudianteLogin.getMatricula(), idproducto);

            Intent intent = new Intent(this, MakeOrderActivity.class);
            intent.putExtra("pedido", pedido);
            startActivity(intent);

        });
    }

    public void buscarProducto(String nombre){

        retrofit = new Retrofit.Builder()
                .baseUrl(API.getUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ProductoResponse> call = apiService.productoGetByName(nombre);
            call.enqueue(new Callback<ProductoResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProductoResponse> call, @NonNull Response<ProductoResponse> response) {

                if((response.body().getProducto() == null)){
                    mostrarToast("Ha ocurrido un error, inténtalo de nuevo.");
                }else {
                    idproducto = response.body().getProducto().getId_producto();
                    precioProdcuto = response.body().getProducto().getPrecio();
                    mostrarDatos(response.body().getProducto());
                }
            }
            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable t) {
                mostrarToast("Ha ocurrido un error");
            }
        });
    }

    public void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void mostrarDatos(Producto producto){
        TextView textViewNombre = binding.textViewTitulo;
        TextView textViewNombre2 = binding.textView3;
        ImageView imageViewFoto = binding.imageViewProducto;
        TextView textViewDescripcion = binding.descripcionProducto;
        TextView textViewPrecio = binding.precioProducto;
        TextView textViewCantidad = binding.cantidadProducto;
        TextView textViewHorario = binding.horarioProducto;
        TextView textViewPuntoEncuentro = binding.puntoEncuentro;

        textViewNombre.setText(producto.getNombre());
        textViewNombre2.setText(producto.getNombre());
        textViewDescripcion.setText(producto.getDescripcion());
        textViewPrecio.setText("Precio: $" + producto.getPrecio().toString());
        String cantidadd = Integer.toString(producto.getCantidadDisponible());
        textViewCantidad.setText("Cantidad disponible: " + cantidadd);
        textViewHorario.setText("Horario: de " + producto.getHoraVentaInicial() +" a " + producto.getHoraVentaFinal());
        textViewPuntoEncuentro.setText("Entrega en: " + producto.getPuntoEncuentro());

        byte[] byteArray = Base64.decode(producto.getFoto(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageViewFoto.setImageBitmap(bitmap);
    }

    public static String randomID(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
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
