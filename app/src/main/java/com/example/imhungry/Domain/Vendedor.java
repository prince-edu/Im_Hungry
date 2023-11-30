package com.example.imhungry.Domain;

public class Vendedor {
    private String idVendedor;
    private String matricula;
    private int idProducto;

    public Vendedor(String idVendedor, String matricula, int idProducto) {
        this.idVendedor = idVendedor;
        this.matricula = matricula;
        this.idProducto = idProducto;
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

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}
