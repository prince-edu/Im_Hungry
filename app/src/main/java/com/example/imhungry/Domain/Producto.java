package com.example.imhungry.Domain;

public class Producto {
    private int id_producto;
    private String nombre;
    private String descripcion;
    private int cantidadDisponible;
    private String horaVentaInicial;
    private String horaVentaFinal;
    private String puntoEncuentro;
    private Double precio;
    private String estado;
    private String foto;

    public Producto(int id_producto, String nombre, String descripcion, int cantidadDisponible,
                    String horaVentaInicial, String horaVentaFinal, String puntoEncuentro, Double precio,
                    String estado, String foto) {
        this.id_producto = id_producto;
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

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
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

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getHoraVentaInicial() {
        return horaVentaInicial;
    }

    public void setHoraVentaInicial(String horaVentaInicial) {
        this.horaVentaInicial = horaVentaInicial;
    }

    public String getHoraVentaFinal() {
        return horaVentaFinal;
    }

    public void setHoraVentaFinal(String horaVentaFinal) {
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

