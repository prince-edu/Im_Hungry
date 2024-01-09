package com.example.imhungry.Domain;

import com.squareup.moshi.Json;

public class PedidoResponse {
        @Json(name = "pedido")
        private Pedido pedido;

        public PedidoResponse(){}

        public Pedido getPedido(){
            return pedido;
        }

        public void setPedido(Pedido pedido){
            this.pedido = pedido;
        }

        public String toString(){
            return "PedidoResponse(" + "pedido: " + pedido + ")";
        }

}
