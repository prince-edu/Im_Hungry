package com.example.imhungry.Domain;

import android.graphics.Bitmap;

import com.squareup.moshi.Json;

public class Estudiante {
    private String matricula;
    private String nombre;

    @Json(name="apellido_paterno")
    private String apellidoPaterno;
    @Json(name="apellido_materno")
    private String apellidoMaterno;

    @Json(name="correo_institucional")
    private String correoInstitucional;
    private String password;
    private String perfilVendedor;
    private String perfilComprador;
    @Json(name="foto_perfil")
    private String fotoPerfil;
    @Json(name="foto_credencial")
    private String fotoCredencial;


    public Estudiante(String matricula, String password){
        this.matricula = matricula;
        this.password = password;
    }
    public Estudiante(){

    }
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

    public String getPerfilVendedor() {
        return perfilVendedor;
    }

    public void setPerfilVendedor(String perfilVendedor) {
        this.perfilVendedor = perfilVendedor;
    }

    public String getPerfilComprador() {
        return perfilComprador;
    }

    public void setPerfilComprador(String perfilComprador) {
        this.perfilComprador = perfilComprador;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoCredencial(String fotoCredencial) {
        this.fotoCredencial = fotoCredencial;
    }
    public String getFotoCredencial() {
        return fotoCredencial;
    }
}