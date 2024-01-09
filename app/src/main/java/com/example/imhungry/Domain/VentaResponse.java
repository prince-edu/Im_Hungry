package com.example.imhungry.Domain;

import com.squareup.moshi.Json;

public class VentaResponse {

        @Json(name = "venta")
        private Venta venta;

        public VentaResponse(){}

        public Venta getVenta(){
            return venta;
        }

        public void setVenta(Venta venta){
            this.venta = venta;
        }

        public String toString(){
            return "VentaResponse(" + "venta: " + venta + ")";
        }
}
