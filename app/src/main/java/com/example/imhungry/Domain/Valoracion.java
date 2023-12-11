package com.example.imhungry.Domain;

public class Valoracion {
    private int id_valoracion;
    private String descripcion;
    private int calificacion;

    public Valoracion(int id_valoracion, String descripcion, int calificacion) {
        this.id_valoracion = id_valoracion;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
    }

    public int getId_valoracion() {
        return id_valoracion;
    }

    public void setId_valoracion(int id_valoracion) {
        this.id_valoracion = id_valoracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
