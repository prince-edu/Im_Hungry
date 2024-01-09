package com.example.imhungry.ui.products;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.imhungry.Domain.Producto;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;
import com.example.imhungry.databinding.FragmentProductsBinding;
import com.example.imhungry.gui.MainMenuCompradorActivity;

import android.util.Base64;

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
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
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
                    mostrarToast("Ha ocurrido un errorr");
                }else {

                    mostrarToast("Éxito");

                    Producto productos[] = response.body();
                    List<Producto> listaProductos = new ArrayList<>(Arrays.asList(productos));
                    RecyclerView.Adapter adapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                        @NonNull
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            // Crear la vista para cada elemento del RecyclerView
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
                            return new ProductoViewHolder(view);
                        }

                        @Override
                        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                            // Establecer los datos para cada elemento del RecyclerView
                            if (holder instanceof ProductoViewHolder) {
                                Producto producto = listaProductos.get(position);
                                ProductoViewHolder productoViewHolder = (ProductoViewHolder) holder;

                                productoViewHolder.textViewNombre.setText(producto.getNombre());
                                productoViewHolder.textViewPrecio.setText(String.valueOf(producto.getPrecio()));

                                Bitmap foto = convertirStringABitmap(producto.getFoto());
                                if (foto != null) {
                                    productoViewHolder.imageViewFoto.setImageBitmap(foto);
                                } else {
                                    // Manejar el caso en que el bitmap sea nulo
                                    productoViewHolder.imageViewFoto.setImageResource(R.drawable.im_hungry_icon);
                                }

                                productoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // Acciones al hacer clic en un elemento
                                        Intent intent = new Intent(getActivity(), ViewProductActivity.class);
                                        intent.putExtra("nombre", producto.getNombre());
                                        startActivity(intent);
                                    }
                                });
                            }
                        }

                        @Override
                        public int getItemCount() {
                            return listaProductos.size();
                        }
                    };

// Establecer el adaptador al RecyclerView
                    rv.setAdapter(adapter);

                }
            }
            @Override
            public void onFailure(Call<Producto[]> call, Throwable t) {
                mostrarToast("Error");
            }
        });
    }
    private Bitmap convertirStringABitmap(String fotoString) {
        try {
            byte[] encodeByte = Base64.decode(fotoString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
