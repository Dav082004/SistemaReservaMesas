package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ConfiguracionFranja")
public class ConfiguracionFranja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFranja;

    private String franjaHoraria;
    private Integer capacidadMaxima;
    private Integer cantidadMesas;

    // Constructores
    public ConfiguracionFranja() {
    }

    public ConfiguracionFranja(String franjaHoraria, Integer capacidadMaxima, Integer cantidadMesas) {
        this.franjaHoraria = franjaHoraria;
        this.capacidadMaxima = capacidadMaxima;
        this.cantidadMesas = cantidadMesas;
    }

    // Getters y Setters
    public Integer getIdFranja() {
        return idFranja;
    }

    public void setIdFranja(Integer idFranja) {
        this.idFranja = idFranja;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public Integer getCantidadMesas() {
        return cantidadMesas;
    }

    public void setCantidadMesas(Integer cantidadMesas) {
        this.cantidadMesas = cantidadMesas;
    }

    @Override
    public String toString() {
        return "ConfiguracionFranja{" +
                "idFranja=" + idFranja +
                ", franjaHoraria='" + franjaHoraria + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", cantidadMesas=" + cantidadMesas +
                '}';
    }
}