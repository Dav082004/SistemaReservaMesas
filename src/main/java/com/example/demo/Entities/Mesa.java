package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMesa;

    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name = "idTipoMesa", nullable = false)
    private TipoMesa tipoMesa;

    // Constructores
    public Mesa() {
    }

    public Mesa(Integer capacidad, TipoMesa tipoMesa) {
        this.capacidad = capacidad;
        this.tipoMesa = tipoMesa;
    }

    // Getters y Setters
    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public TipoMesa getTipoMesa() {
        return tipoMesa;
    }

    public void setTipoMesa(TipoMesa tipoMesa) {
        this.tipoMesa = tipoMesa;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "idMesa=" + idMesa +
                ", capacidad=" + capacidad +
                ", tipoMesa=" + (tipoMesa != null ? tipoMesa.getNombre() : "null") +
                '}';
    }
}