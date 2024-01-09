package com.example.imhungry.ui.products;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imhungry.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDescripcion;
        TextView textViewCalificacion;


        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            // Referencias a las vistas dentro de item_producto.xml
            textViewDescripcion = itemView.findViewById(R.id.recycler_review_descripcion);
            textViewCalificacion = itemView.findViewById(R.id.recycler_review_calificacion);
        }
}
