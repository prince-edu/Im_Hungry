package com.example.imhungry.Domain;

public class ProductosFavoritos {
    private String idFavoritos;
    private String matricula;
    private int idProducto;

    public ProductosFavoritos(String idFavoritos, String matricula, int idProducto) {
        this.idFavoritos = idFavoritos;
        this.matricula = matricula;
        this.idProducto = idProducto;
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

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}
