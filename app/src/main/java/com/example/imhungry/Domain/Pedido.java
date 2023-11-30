package com.example.imhungry.Domain;

import java.util.Date;

public class Pedido {
    private int idPedido;
    private String preferencias;
    private Date fechaPedido;
    private Double precioTotal;
    private String estado;
    private int idVenta;
    private String matricula;
    private int idProducto;

    public Pedido(int idPedido, String preferencias, Date fechaPedido, Double precioTotal,
                  String estado, int idVenta, String matricula, int idProducto) {
        this.idPedido = idPedido;
        this.preferencias = preferencias;
        this.fechaPedido = fechaPedido;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.idVenta = idVenta;
        this.matricula = matricula;
        this.idProducto = idProducto;
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

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}
