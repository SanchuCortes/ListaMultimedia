package com.example.listamultimedia;

import java.io.Serializable;


public class ModeloItem implements Serializable {
    private String titulo;
    private String descripcion;
    private int tipo;
    private String url;


    public ModeloItem(String titulo, String descripcion, int tipo, String url) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.url = url;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public String getUrl() {
        return url;
    }
}
