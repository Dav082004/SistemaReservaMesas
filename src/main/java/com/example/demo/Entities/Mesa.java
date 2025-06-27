package com.example.demo.Entities;


public class Mesa {

    private int idMesa;
    private int idTipoMesa; // Foreign Key a TipoMesa
    private int capacidad;

    // Constructor vacío
    public Mesa() {
    }

    // Getters y Setters
    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdTipoMesa() {
        return idTipoMesa;
    }

    public void setIdTipoMesa(int idTipoMesa) {
        this.idTipoMesa = idTipoMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}