package com.example.imhungry.Domain;

import java.util.Date;

public class Venta {
    private int id_venta;
    private int cantidad;
    private String fecha_venta;
    private double precio_total;

    public Venta(int id_venta, int cantidad, String fecha_venta, double precio_total) {
        this.id_venta = id_venta;
        this.cantidad = cantidad;
        this.fecha_venta = fecha_venta;
        this.precio_total = precio_total;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }
}
