package com.example.imhungry.ui.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imhungry.Domain.Producto;
import com.example.imhungry.Domain.Valoracion;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;
import com.example.imhungry.databinding.ActivityReviewsBinding;
import com.example.imhungry.databinding.FragmentHomeBinding;
import com.example.imhungry.ui.home.ProductoViewHolderComprador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ReviewsActivity extends AppCompatActivity {

    private ActivityReviewsBinding binding;
    Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_reviews);

        binding = ActivityReviewsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        retrofit = new Retrofit.Builder()
                .baseUrl(API.getUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        RecyclerView recyclerViewReviews = findViewById(R.id.recycler_reviews);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewReviews.setLayoutManager(staggeredGridLayoutManager);
        api(recyclerViewReviews);
    }

    public void api(RecyclerView rv){

        ApiService apiService = retrofit.create(ApiService.class);
        Call<Valoracion[]> call = apiService.valoracionGetAll();
        call.enqueue(new Callback<Valoracion[]>() {
            @Override
            public void onResponse(Call<Valoracion[]> call, Response<Valoracion[]> response) {
                if((response.body() == null)){
                    mostrarToast("Ha ocurrido un error");
                }else {

                    mostrarToast("Éxito");

                    Valoracion valoraciones[] = response.body();
                    List<Valoracion> listaValoraciones = new ArrayList<>(Arrays.asList(valoraciones));
                    RecyclerView.Adapter adapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                        @NonNull
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            // Crear la vista para cada elemento del RecyclerView
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reviews_layout, parent, false);
                            return new ReviewViewHolder(view);
                        }

                        @Override
                        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                            // Establecer los datos para cada elemento del RecyclerView
                            if (holder instanceof ReviewViewHolder) {
                                Valoracion valoracion = listaValoraciones.get(position);
                                ReviewViewHolder reviewViewHolder = (ReviewViewHolder  ) holder;

                                reviewViewHolder.textViewDescripcion.setText(valoracion.getDescripcion());
                                reviewViewHolder.textViewCalificacion.setText(String.valueOf(valoracion.getCalificacion()));
                            }
                        }

                        @Override
                        public int getItemCount() {
                            return listaValoraciones.size();
                        }
                    };

                    // Establecer el adaptador al RecyclerView
                    rv.setAdapter(adapter);

                }
            }
            @Override
            public void onFailure(Call<Valoracion[]> call, Throwable t) {
                mostrarToast("Error");
            }
        });
    }

    public void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}