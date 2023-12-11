package com.example.imhungry.Domain;

public class Vendedor {
    private String idVendedor;
    private String matricula;
    private int id_producto;

    public Vendedor(String idVendedor, String matricula, int id_producto) {
        this.idVendedor = idVendedor;
        this.matricula = matricula;
        this.id_producto = id_producto;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
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
