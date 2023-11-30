package com.example.imhungry.Domain;

import java.util.Date;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private Integer cantidadDisponible;
    private Date horaVentaInicial;
    private Date horaVentaFinal;
    private String puntoEncuentro;
    private Double precio;
    private String estado;
    private String foto;

    public Producto(int idProducto, String nombre, String descripcion, Integer cantidadDisponible,
                    Date horaVentaInicial, Date horaVentaFinal, String puntoEncuentro,
                    Double precio, String estado, String foto) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadDisponible = cantidadDisponible;
        this.horaVentaInicial = horaVentaInicial;
        this.horaVentaFinal = horaVentaFinal;
        this.puntoEncuentro = puntoEncuentro;
        this.precio = precio;
        this.estado = estado;
        this.foto = foto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Date getHoraVentaInicial() {
        return horaVentaInicial;
    }

    public void setHoraVentaInicial(Date horaVentaInicial) {
        this.horaVentaInicial = horaVentaInicial;
    }

    public Date getHoraVentaFinal() {
        return horaVentaFinal;
    }

    public void setHoraVentaFinal(Date horaVentaFinal) {
        this.horaVentaFinal = horaVentaFinal;
    }

    public String getPuntoEncuentro() {
        return puntoEncuentro;
    }

    public void setPuntoEncuentro(String puntoEncuentro) {
        this.puntoEncuentro = puntoEncuentro;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

