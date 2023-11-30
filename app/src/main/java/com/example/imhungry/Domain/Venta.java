package com.example.imhungry.Domain;

import java.util.Date;

public class Venta {
    private int idVenta;
    private Integer cantidad;
    private Date fechaVenta;
    private Double precioTotal;

    public Venta(int idVenta, Integer cantidad, Date fechaVenta, Double precioTotal) {
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.fechaVenta = fechaVenta;
        this.precioTotal = precioTotal;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
