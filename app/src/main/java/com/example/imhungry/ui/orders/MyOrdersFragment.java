package com.example.imhungry.ui.orders;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.example.imhungry.Domain.Pedido;
import com.example.imhungry.Domain.Producto;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;
import com.example.imhungry.databinding.FragmentMyOrdersBinding;
import com.example.imhungry.ui.home.ProductoViewHolderComprador;
import com.example.imhungry.ui.products.ViewProductActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MyOrdersFragment extends Fragment {
    private FragmentMyOrdersBinding binding;
    Retrofit retrofit;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OrdersViewModel ordersViewModel =
                new ViewModelProvider(this).get(OrdersViewModel.class);

        binding = FragmentMyOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        retrofit = new Retrofit.Builder()
                .baseUrl(API.getUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        RecyclerView recyclerView = binding.recyclerOrdersComprador;
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        api(recyclerView);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void api(RecyclerView rv){

        ApiService apiService = retrofit.create(ApiService.class);
        Call<Pedido[]> call = apiService.pedidosGetAll();
        call.enqueue(new Callback<Pedido[]>() {
            @Override
            public void onResponse(Call<Pedido[]> call, Response<Pedido[]> response) {
                if((response.body() == null)){
                    mostrarToast("Ha ocurrido un error");
                }else {

                    mostrarToast("Éxito");

                    Pedido pedidos[] = response.body();
                    List<Pedido> listaPedidos = new ArrayList<>(Arrays.asList(pedidos));
                    RecyclerView.Adapter adapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                        @NonNull
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            // Crear la vista para cada elemento del RecyclerView
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido_layout, parent, false);
                            return new PedidoViewHolder(view);
                        }

                        @Override
                        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                            // Establecer los datos para cada elemento del RecyclerView
                            if (holder instanceof PedidoViewHolder) {
                                Pedido pedido = listaPedidos.get(position);
                                PedidoViewHolder pedidoViewHolder = (PedidoViewHolder  ) holder;

                                pedidoViewHolder.textViewId.setText(String.valueOf(pedido.getIdPedido()));
                                pedidoViewHolder.textViewFecha.setText(String.valueOf(pedido.getFechaPedido()));
                                pedidoViewHolder.textViewEstado.setText(pedido.getEstado());

                                pedidoViewHolder.itemView.setOnClickListener(new View.OnClickListener(){

                                    @Override
                                    public void onClick(View v) {
                                        // Acciones al hacer clic en un elemento
                                        Intent intent = new Intent(getActivity(), ViewProductActivity.class);
                                        intent.putExtra("pedido", pedido);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }

                        @Override
                        public int getItemCount() {
                            return listaPedidos.size();
                        }
                    };

                    rv.setAdapter(adapter);

                }
            }
            @Override
            public void onFailure(Call<Pedido[]> call, Throwable t) {
                mostrarToast("Error");
            }
        });
    }
    public void mostrarToast(String mensaje){
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}