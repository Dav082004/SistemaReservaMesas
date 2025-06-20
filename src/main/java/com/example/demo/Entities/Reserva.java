package com.example.demo.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
    private LocalDate fecha;
    private Integer numeroPersonas;
    private String estado;
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "idFranja", nullable = false)
    private ConfiguracionFranja franja;

    @ManyToOne
    @JoinColumn(name = "idMesa", nullable = false)
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    // Constructores
    public Reserva() {
    }

    public Reserva(String nombreCliente, String correoCliente, String telefonoCliente,
            LocalDate fecha, Integer numeroPersonas, String estado, String comentarios) {
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
        this.estado = estado;
        this.comentarios = comentarios;
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

    public ConfiguracionFranja getFranja() {
        return franja;
    }

    public void setFranja(ConfiguracionFranja franja) {
        this.franja = franja;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", correoCliente='" + correoCliente + '\'' +
                ", fecha=" + fecha +
                ", numeroPersonas=" + numeroPersonas +
                ", estado='" + estado + '\'' +
                '}';
    }
}