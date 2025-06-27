package com.example.demo.Entities;

public class ConfiguracionFranja {

    private int idFranja;
    private String franjaHoraria;
    private int capacidadMaxima;
    private int cantidadMesas;

    // Constructor vacío
    public ConfiguracionFranja() {
    }

    // Getters y Setters
    public int getIdFranja() {
        return idFranja;
    }

    public void setIdFranja(int idFranja) {
        this.idFranja = idFranja;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getCantidadMesas() {
        return cantidadMesas;
    }

    public void setCantidadMesas(int cantidadMesas) {
        this.cantidadMesas = cantidadMesas;
    }
}