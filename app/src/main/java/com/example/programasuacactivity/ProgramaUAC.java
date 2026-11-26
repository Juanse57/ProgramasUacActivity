package com.example.programasuacactivity;

import java.io.Serializable;

public class ProgramaUAC implements Serializable {
    private String programa;
    private String facultad;
    private String descripcion;
    private String imagenUrl;

    public ProgramaUAC(String programa, String facultad, String descripcion, String imagenUrl) {
        this.programa = programa;
        this.facultad = facultad;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
    }

    public String getPrograma() {
        return programa;
    }

    public String getFacultad() {
        return facultad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }
}