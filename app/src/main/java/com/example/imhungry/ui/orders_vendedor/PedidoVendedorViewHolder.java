package com.example.imhungry.ui.orders_vendedor;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imhungry.R;

public class PedidoVendedorViewHolder extends RecyclerView.ViewHolder {

    TextView textViewId;
    TextView textViewFecha;
    TextView textViewEstado;

    public PedidoVendedorViewHolder(@NonNull View itemView) {
        super(itemView);
        // Referencias a las vistas dentro de item_producto.xml
        textViewId = itemView.findViewById(R.id.recycler_id_pedido_vendedor);
        textViewFecha = itemView.findViewById(R.id.recycler_fecha_pedido_vendedor);
        textViewEstado = itemView.findViewById(R.id.recycler_estado_pedido_vendedor);
    }
}