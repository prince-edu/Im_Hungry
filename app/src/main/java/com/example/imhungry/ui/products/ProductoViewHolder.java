package com.example.imhungry.ui.products;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imhungry.Domain.Producto;
import com.example.imhungry.R;

import java.util.List;

public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        TextView textViewPrecio;
        ImageView imageViewFoto;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Referencias a las vistas dentro de item_producto.xml
            textViewNombre = itemView.findViewById(R.id.recycler_nombre);
            textViewPrecio = itemView.findViewById(R.id.recycler_precio);
            imageViewFoto = itemView.findViewById(R.id.recycler_foto);
        }
    }