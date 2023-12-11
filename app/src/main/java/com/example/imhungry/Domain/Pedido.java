package com.example.imhungry.Domain;

import java.util.Date;

public class Pedido {
    private int idPedido;
    private String preferencias;
    private Date fechaPedido;
    private Double precioTotal;
    private String estado;
    private int id_venta;
    private String matricula;
    private int id_producto;

    public Pedido(int idPedido, String preferencias, Date fechaPedido, Double precioTotal,
                  String estado, int id_venta, String matricula, int id_producto) {
        this.idPedido = idPedido;
        this.preferencias = preferencias;
        this.fechaPedido = fechaPedido;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.id_venta = id_venta;
        this.matricula = matricula;
        this.id_producto = id_producto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
}
