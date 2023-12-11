package com.example.imhungry.Domain;

public class ProductosFavoritos {
    private String idFavoritos;
    private String matricula;
    private int id_producto;

    public ProductosFavoritos(String idFavoritos, String matricula, int id_producto) {
        this.idFavoritos = idFavoritos;
        this.matricula = matricula;
        this.id_producto = id_producto;
    }

    public String getIdFavoritos() {
        return idFavoritos;
    }

    public void setIdFavoritos(String idFavoritos) {
        this.idFavoritos = idFavoritos;
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
