package com.example.demo.dto;

import java.time.LocalDate;

public class ReservaDTO {
    private Integer idReserva;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
    private LocalDate fecha;
    private Integer numeroPersonas;
    private String estado;
    private String comentarios;
    private String franjaHoraria;
    private String tipoMesa;
    private Integer numeroMesa;

    // Constructores
    public ReservaDTO() {
    }

    public ReservaDTO(Integer idReserva, String nombreCliente, String correoCliente,
            String telefonoCliente, LocalDate fecha, Integer numeroPersonas,
            String estado, String comentarios, String franjaHoraria,
            String tipoMesa, Integer numeroMesa) {
        this.idReserva = idReserva;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
        this.estado = estado;
        this.comentarios = comentarios;
        this.franjaHoraria = franjaHoraria;
        this.tipoMesa = tipoMesa;
        this.numeroMesa = numeroMesa;
    }

    // Getters y Setters
    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public String getTipoMesa() {
        return tipoMesa;
    }

    public void setTipoMesa(String tipoMesa) {
        this.tipoMesa = tipoMesa;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "idReserva=" + idReserva +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", fecha=" + fecha +
                ", numeroPersonas=" + numeroPersonas +
                ", estado='" + estado + '\'' +
                ", franjaHoraria='" + franjaHoraria + '\'' +
                ", tipoMesa='" + tipoMesa + '\'' +
                '}';
    }
}
