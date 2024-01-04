package com.example.imhungry.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imhungry.R;

public class ProductoViewHolderComprador extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        TextView textViewPrecio;
        ImageView imageViewFoto;

        public ProductoViewHolderComprador(@NonNull View itemView) {
            super(itemView);
            // Referencias a las vistas dentro de item_producto.xml
            textViewNombre = itemView.findViewById(R.id.recycler_nombre);
            textViewPrecio = itemView.findViewById(R.id.recycler_precio);
            imageViewFoto = itemView.findViewById(R.id.recycler_foto);
        }
    }