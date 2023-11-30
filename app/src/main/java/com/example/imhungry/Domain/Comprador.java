package com.example.imhungry.Domain;

public class Comprador {
    private String idComprador;
    private String matricula;
    private int idProducto;

    public Comprador(String idComprador, String matricula, int idProducto) {
        this.idComprador = idComprador;
        this.matricula = matricula;
        this.idProducto = idProducto;
    }

    public String getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(String idComprador) {
        this.idComprador = idComprador;
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

