package com.example.imhungry.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.imhungry.Domain.EstudianteResponse;
import com.example.imhungry.Domain.Producto;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.databinding.FragmentProductsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MyProductsFragment extends Fragment {
    private FragmentProductsBinding binding;
    Retrofit retrofit;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProductsViewModel productsViewModel =
                new ViewModelProvider(this).get(ProductsViewModel.class);

        binding = FragmentProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        retrofit = new Retrofit.Builder()
                .baseUrl(API.getUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        RecyclerView recyclerView = binding.recyclerProductos;
        api(recyclerView);


        final TextView textView = binding.textProducts;
        productsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void api(RecyclerView rv){

        ApiService apiService = retrofit.create(ApiService.class);
        Call<Producto[]> call = apiService.productoGetAll();
        call.enqueue(new Callback<Producto[]>() {
            @Override
            public void onResponse(Call<Producto[]> call, Response<Producto[]> response) {
                if((response.body() == null)){
                    mostrarToast("Ha ocurrido un error");
                }else {

                    mostrarToast("Éxito");

                    int spanCount = 2; // Número de columnas en la cuadrícula
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);

                    Producto productos[] = response.body();
                    List<Producto> listaProductos = new ArrayList<>(Arrays.asList(productos));
                    ProductsAdapter productsAdapter = new ProductsAdapter(listaProductos);
                    rv.setAdapter(productsAdapter);
                    rv.setLayoutManager(staggeredGridLayoutManager);

                }
            }
            @Override
            public void onFailure(Call<Producto[]> call, Throwable t) {
                mostrarToast("Error");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void mostrarToast(String mensaje){
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
