package com.example.imhungry.Domain;

public class Comprador {
    private String idComprador;
    private String matricula;
    private int id_producto;

    public Comprador(String idComprador, String matricula, int id_producto) {
        this.idComprador = idComprador;
        this.matricula = matricula;
        this.id_producto = id_producto;
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

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
}

