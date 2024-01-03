package com.example.imhungry.Domain;

import com.squareup.moshi.Json;

public class ProductoResponse {

    @Json(name = "producto")
    private Producto producto;

    public ProductoResponse(){}

    public Producto getProducto(){
        return producto;
    }

    public void setProducto(Producto producto){
        this.producto = producto;
    }

    public String toString(){
        return "ProductoResponse(" + "producto: " + producto + ")";
    }

}
