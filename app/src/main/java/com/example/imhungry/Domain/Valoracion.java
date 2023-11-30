package com.example.imhungry.Domain;

public class Valoracion {
    private int idValoracion;
    private String descripcion;
    private Integer calificacion;

    public Valoracion(int idValoracion, String descripcion, Integer calificacion) {
        this.idValoracion = idValoracion;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
    }

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
}
