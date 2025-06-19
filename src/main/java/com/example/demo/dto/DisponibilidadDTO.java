package com.example.demo.dto;

import java.time.LocalDate;

/**
 * DTO para el transporte de información de disponibilidad de mesas
 * 
 * Contiene información sobre la disponibilidad de mesas para una fecha
 * y franja horaria específica, incluyendo detalles sobre capacidad y estado
 */
public class DisponibilidadDTO {
    
    private LocalDate fecha;
    private Integer idFranja;
    private String franjaHoraria;
    private String descripcionFranja;
    private Integer mesasDisponibles;
    private Integer mesasTotales;
    private Integer capacidadDisponible;
    private boolean disponible;
    private String mensaje;
    private String tipoMesa;
    private Integer idTipoMesa;

    /**
     * Constructor vacío requerido para el framework
     */
    public DisponibilidadDTO() {
    }

    /**
     * Constructor con parámetros básicos
     * 
     * @param fecha Fecha de la consulta
     * @param idFranja ID de la franja horaria
     * @param franjaHoraria Descripción de la franja horaria
     * @param mesasDisponibles Número de mesas disponibles
     * @param mesasTotales Número total de mesas
     * @param disponible Si hay disponibilidad o no
     */
    public DisponibilidadDTO(LocalDate fecha, Integer idFranja, String franjaHoraria, 
                           Integer mesasDisponibles, Integer mesasTotales, boolean disponible) {
        this.fecha = fecha;
        this.idFranja = idFranja;
        this.franjaHoraria = franjaHoraria;
        this.mesasDisponibles = mesasDisponibles;
        this.mesasTotales = mesasTotales;
        this.disponible = disponible;
    }

    // Getters y Setters con documentación

    /**
     * Obtiene la fecha de la consulta de disponibilidad
     * @return Fecha de la consulta
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la consulta
     * @param fecha Fecha de la consulta
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el ID de la franja horaria
     * @return ID de la franja horaria
     */
    public Integer getIdFranja() {
        return idFranja;
    }

    /**
     * Establece el ID de la franja horaria
     * @param idFranja ID de la franja horaria
     */
    public void setIdFranja(Integer idFranja) {
        this.idFranja = idFranja;
    }

    /**
     * Obtiene la descripción de la franja horaria (ej: "12:00 - 14:00")
     * @return Descripción de la franja horaria
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
     * Obtiene una descripción más detallada de la franja
     * @return Descripción detallada de la franja
     */
    public String getDescripcionFranja() {
        return descripcionFranja;
    }

    /**
     * Establece la descripción detallada de la franja
     * @param descripcionFranja Descripción detallada
     */
    public void setDescripcionFranja(String descripcionFranja) {
        this.descripcionFranja = descripcionFranja;
    }

    /**
     * Obtiene el número de mesas disponibles para reserva
     * @return Número de mesas disponibles
     */
    public Integer getMesasDisponibles() {
        return mesasDisponibles;
    }

    /**
     * Establece el número de mesas disponibles
     * @param mesasDisponibles Número de mesas disponibles
     */
    public void setMesasDisponibles(Integer mesasDisponibles) {
        this.mesasDisponibles = mesasDisponibles;
    }

    /**
     * Obtiene el número total de mesas del restaurante
     * @return Número total de mesas
     */
    public Integer getMesasTotales() {
        return mesasTotales;
    }

    /**
     * Establece el número total de mesas
     * @param mesasTotales Número total de mesas
     */
    public void setMesasTotales(Integer mesasTotales) {
        this.mesasTotales = mesasTotales;
    }

    /**
     * Obtiene la capacidad total disponible (personas)
     * @return Capacidad disponible en personas
     */
    public Integer getCapacidadDisponible() {
        return capacidadDisponible;
    }

    /**
     * Establece la capacidad disponible
     * @param capacidadDisponible Capacidad disponible en personas
     */
    public void setCapacidadDisponible(Integer capacidadDisponible) {
        this.capacidadDisponible = capacidadDisponible;
    }

    /**
     * Indica si hay disponibilidad para hacer una reserva
     * @return true si hay disponibilidad, false en caso contrario
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece el estado de disponibilidad
     * @param disponible Estado de disponibilidad
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene un mensaje explicativo sobre la disponibilidad
     * @return Mensaje informativo
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece un mensaje explicativo
     * @param mensaje Mensaje informativo
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el tipo de mesa para esta disponibilidad
     * @return Nombre del tipo de mesa
     */
    public String getTipoMesa() {
        return tipoMesa;
    }

    /**
     * Establece el tipo de mesa
     * @param tipoMesa Nombre del tipo de mesa
     */
    public void setTipoMesa(String tipoMesa) {
        this.tipoMesa = tipoMesa;
    }

    /**
     * Obtiene el ID del tipo de mesa
     * @return ID del tipo de mesa
     */
    public Integer getIdTipoMesa() {
        return idTipoMesa;
    }

    /**
     * Establece el ID del tipo de mesa
     * @param idTipoMesa ID del tipo de mesa
     */
    public void setIdTipoMesa(Integer idTipoMesa) {
        this.idTipoMesa = idTipoMesa;
    }

    /**
     * Calcula el porcentaje de ocupación para esta franja
     * @return Porcentaje de ocupación (0.0 a 100.0)
     */
    public Double getPorcentajeOcupacion() {
        if (mesasTotales == null || mesasTotales == 0) {
            return 0.0;
        }
        Integer mesasOcupadas = mesasTotales - (mesasDisponibles != null ? mesasDisponibles : 0);
        return (mesasOcupadas.doubleValue() / mesasTotales.doubleValue()) * 100.0;
    }

    /**
     * Genera un mensaje automático basado en la disponibilidad
     * @return Mensaje descriptivo del estado
     */
    public String generarMensajeAutomatico() {
        if (disponible) {
            if (mesasDisponibles != null && mesasDisponibles > 5) {
                return "Excelente disponibilidad";
            } else if (mesasDisponibles != null && mesasDisponibles > 2) {
                return "Buena disponibilidad";
            } else {
                return "Disponibilidad limitada";
            }
        } else {
            return "Sin disponibilidad";
        }
    }
}
