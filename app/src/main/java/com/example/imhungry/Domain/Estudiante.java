package com.example.imhungry.Domain;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.squareup.moshi.Json;

//@Json(name="apellido_paterno")
public class Estudiante {
    private String matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoInstitucional;
    private String password;
    private String tipoVendedor;
    private String tipoComprador;
    private String fotoPerfil;
    private String fotoCredencial;

    public Estudiante(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno,
                      String correoInstitucional, String password, String tipoVendedor, String tipoComprador,
                      String fotoPerfil, String fotoCredencial) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoInstitucional = correoInstitucional;
        this.password = password;
        this.tipoVendedor = tipoVendedor;
        this.tipoComprador = tipoComprador;
        this.fotoPerfil = fotoPerfil;
        this.fotoCredencial = fotoCredencial;
    }

    public Estudiante(String matricula, String password){
        this.matricula = matricula;
        this.password = password;
    }
    public Estudiante(){};

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoVendedor() {
        return tipoVendedor;
    }

    public void setTipoVendedor(String tipoVendedor) {
        this.tipoVendedor = tipoVendedor;
    }

    public String getTipoComprador() {
        return tipoComprador;
    }

    public void setTipoComprador(String tipoComprador) {
        this.tipoComprador = tipoComprador;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getFotoCredencial() {
        return fotoCredencial;
    }

    public void setFotoCredencial(String fotoCredencial) {
        this.fotoCredencial = fotoCredencial;
    }

    @NonNull
    @Override
    public String toString() {
        return matricula + " " + nombre + " " + apellidoPaterno + " " + apellidoMaterno + " " + correoInstitucional +
                " " + password + "  " + fotoPerfil;
    }
}