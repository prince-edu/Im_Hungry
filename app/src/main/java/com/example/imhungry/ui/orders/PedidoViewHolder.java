package com.example.imhungry.ui.orders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imhungry.R;

public class PedidoViewHolder extends RecyclerView.ViewHolder {

    TextView textViewId;
    TextView textViewFecha;
    TextView textViewEstado;

    public PedidoViewHolder(@NonNull View itemView) {
        super(itemView);
        // Referencias a las vistas dentro de item_producto.xml
        textViewId = itemView.findViewById(R.id.recycler_id_pedido);
        textViewFecha = itemView.findViewById(R.id.recycler_fecha_pedido);
        textViewEstado = itemView.findViewById(R.id.recycler_estado_pedido);
    }
}
