package com.example.demo.dto;

import java.time.LocalDate;

/**
 * DTO para el transporte de datos de reservas
 * 
 * Este objeto se utiliza para transferir información de reservas entre
 * las diferentes capas del sistema (controladores, facades, services)
 * sin exponer las entidades directamente
 */
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
    private String usuarioCreador; // Username del usuario que creó la reserva

    /**
     * Constructor vacío requerido para el framework
     */
    public ReservaDTO() {
    }

    /**
     * Constructor con todos los parámetros
     * 
     * @param idReserva ID único de la reserva
     * @param nombreCliente Nombre completo del cliente
     * @param correoCliente Email del cliente
     * @param telefonoCliente Teléfono del cliente
     * @param fecha Fecha de la reserva
     * @param numeroPersonas Cantidad de personas para la reserva
     * @param estado Estado actual de la reserva
     * @param comentarios Comentarios adicionales
     * @param franjaHoraria Descripción de la franja horaria
     * @param tipoMesa Tipo de mesa asignada
     * @param numeroMesa Número de mesa asignada
     */
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

    // Getters y Setters con documentación

    /**
     * Obtiene el ID único de la reserva
     * @return ID de la reserva
     */
    public Integer getIdReserva() {
        return idReserva;
    }

    /**
     * Establece el ID de la reserva
     * @param idReserva ID único de la reserva
     */
    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Obtiene el nombre completo del cliente
     * @return Nombre del cliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Establece el nombre del cliente
     * @param nombreCliente Nombre completo del cliente
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Obtiene el correo electrónico del cliente
     * @return Email del cliente
     */
    public String getCorreoCliente() {
        return correoCliente;
    }

    /**
     * Establece el correo del cliente
     * @param correoCliente Email del cliente
     */
    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    /**
     * Obtiene el teléfono del cliente
     * @return Teléfono del cliente
     */
    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    /**
     * Establece el teléfono del cliente
     * @param telefonoCliente Teléfono del cliente
     */
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    /**
     * Obtiene la fecha de la reserva
     * @return Fecha de la reserva
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la reserva
     * @param fecha Fecha de la reserva
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el número de personas para la reserva
     * @return Cantidad de personas
     */
    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    /**
     * Establece el número de personas
     * @param numeroPersonas Cantidad de personas para la reserva
     */
    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    /**
     * Obtiene el estado actual de la reserva
     * @return Estado de la reserva (CONFIRMADA, POR_CONFIRMAR, CANCELADA)
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la reserva
     * @param estado Nuevo estado de la reserva
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene los comentarios adicionales de la reserva
     * @return Comentarios o null si no hay
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Establece los comentarios de la reserva
     * @param comentarios Comentarios adicionales
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Obtiene la descripción de la franja horaria
     * @return Descripción de la franja horaria (ej: "12:00 - 14:00")
     */
    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    /**
     * Establece la descripción de la franja horaria
     * @param franjaHoraria Descripción de la franja horaria
     */
    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    /**
     * Obtiene el tipo de mesa asignada
     * @return Tipo de mesa
     */
    public String getTipoMesa() {
        return tipoMesa;
    }

    /**
     * Establece el tipo de mesa
     * @param tipoMesa Tipo de mesa asignada
     */
    public void setTipoMesa(String tipoMesa) {
        this.tipoMesa = tipoMesa;
    }

    /**
     * Obtiene el número de mesa asignada
     * @return Número de mesa
     */
    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    /**
     * Establece el número de mesa
     * @param numeroMesa Número de mesa asignada
     */
    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    /**
     * Obtiene el username del usuario que creó la reserva
     * @return Username del usuario creador
     */
    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    /**
     * Establece el usuario creador de la reserva
     * @param usuarioCreador Username del usuario que creó la reserva
     */
    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    /**
     * Verifica si la reserva puede ser cancelada
     * @return true si la reserva puede cancelarse, false en caso contrario
     */
    public boolean puedeSerCancelada() {
        return "POR_CONFIRMAR".equals(estado) || "CONFIRMADA".equals(estado);
    }

    /**
     * Verifica si la reserva está confirmada
     * @return true si la reserva está confirmada
     */
    public boolean estaConfirmada() {
        return "CONFIRMADA".equals(estado);
    }

    /**
     * Verifica si la reserva está cancelada
     * @return true si la reserva está cancelada
     */
    public boolean estaCancelada() {
        return "CANCELADA".equals(estado);
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
                ", numeroMesa=" + numeroMesa +
                '}';
    }
}
