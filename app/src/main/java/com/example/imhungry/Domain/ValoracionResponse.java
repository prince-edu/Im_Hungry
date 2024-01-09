package com.example.imhungry.Domain;

import com.squareup.moshi.Json;

public class ValoracionResponse {

        @Json(name = "valoracion")
        private Valoracion valoracion;

        public ValoracionResponse(){}

        public Valoracion getValoracion(){
            return valoracion;
        }

        public void setValoracion(Valoracion valoracion){
            this.valoracion = valoracion;
        }

        public String toString(){
            return "ValoracionResponse(" + "valoracion: " + valoracion + ")";
        }
}
