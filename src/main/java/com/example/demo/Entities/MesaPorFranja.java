package com.example.demo.Entities;

import java.time.LocalDate;

public class MesaPorFranja {

    private int idMesaPorFranja;
    private int idMesa;
    private int idFranja;
    private LocalDate fecha;
    private boolean disponible;

    // Constructor vacío
    public MesaPorFranja() {
    }

    // Getters y Setters
    public int getIdMesaPorFranja() {
        return idMesaPorFranja;
    }

    public void setIdMesaPorFranja(int idMesaPorFranja) {
        this.idMesaPorFranja = idMesaPorFranja;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdFranja() {
        return idFranja;
    }

    public void setIdFranja(int idFranja) {
        this.idFranja = idFranja;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}