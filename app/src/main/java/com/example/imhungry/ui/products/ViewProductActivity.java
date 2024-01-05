package com.example.imhungry.ui.products;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.imhungry.Domain.Estudiante;
import com.example.imhungry.Domain.EstudianteResponse;
import com.example.imhungry.Domain.Producto;
import com.example.imhungry.Domain.ProductoResponse;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;
import com.example.imhungry.databinding.ActivityViewProductBinding;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ViewProductActivity extends AppCompatActivity {
    private ActivityViewProductBinding binding;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        binding = ActivityViewProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();

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
                    mostrarToast("Ha ocurrido un error, inténtalo de nuevooo.");
                }else {

                    mostrarDatos(response.body().getProducto());
                }
            }
            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable t) {
                mostrarToast("Ha ocurrido un errorrr");
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
        textViewPrecio.setText(producto.getPrecio().toString());
        textViewCantidad.setText(producto.getCantidadDisponible());
        textViewHorario.setText("Horario: de " + producto.getHoraVentaInicial() +" a " + producto.getHoraVentaFinal());
        textViewPuntoEncuentro.setText("Entrega en: " + producto.getPuntoEncuentro());

        //imageViewFoto.
    }
}
