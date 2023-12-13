package com.example.imhungry.Domain;

import com.squareup.moshi.Json;

public class EstudianteResponse {

    @Json(name = "estudiante")
    private Estudiante estudiante;

    public EstudianteResponse(){}

    public Estudiante getEstudiante(){
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante){
        this.estudiante = estudiante;
    }

    public String toString(){
        return "EstudianteResponse(" + "estudiante: " + estudiante + ")";
    }

}
