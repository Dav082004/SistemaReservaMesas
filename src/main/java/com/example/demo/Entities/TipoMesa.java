package com.example.demo.Entities;

public class TipoMesa {

    private int idTipoMesa;
    private String nombre;
    private String descripcion;

    // Constructor vacío
    public TipoMesa() {
    }

    // Getters y Setters
    public int getIdTipoMesa() {
        return idTipoMesa;
    }

    public void setIdTipoMesa(int idTipoMesa) {
        this.idTipoMesa = idTipoMesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
