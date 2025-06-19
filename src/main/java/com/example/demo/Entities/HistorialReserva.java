package com.example.demo.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad para el historial de cambios en reservas
 * 
 * Esta entidad registra todas las acciones realizadas sobre las reservas
 * para mantener un log de auditoría del sistema.
 * 
 * En lugar de @ManyToOne, usamos IDs directos para mejor rendimiento
 * y evitar cargas innecesarias de entidades relacionadas.
 */
@Entity
@Table(name = "HistorialReserva")
public class HistorialReserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorial;

    /**
     * ID de la reserva afectada (en lugar de @ManyToOne)
     * Usamos Integer en lugar de objeto Reserva para:
     * - Mejor rendimiento (no carga la entidad completa)
     * - Evitar problemas de lazy loading
     * - Simplicidad en consultas de auditoría
     */
    @Column(name = "idReserva", nullable = false)
    private Integer idReserva;
    
    /**
     * ID del usuario que realizó la acción (en lugar de @ManyToOne)
     * Usamos Integer en lugar de objeto Usuario por las mismas razones
     */
    @Column(name = "idUsuario")
    private Integer idUsuario;
    
    /**
     * Momento exacto en que se realizó la acción
     */
    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;
    
    /**
     * Acción realizada (CREADA, CONFIRMADA, CANCELADA, MODIFICADA)
     */
    @Column(name = "accion", nullable = false, length = 50)
    private String accion;
    
    /**
     * Estado anterior de la reserva (para saber qué cambió)
     */
    @Column(name = "estadoAnterior", length = 50)
    private String estadoAnterior;
    
    /**
     * Estado nuevo de la reserva
     */
    @Column(name = "estadoNuevo", length = 50)
    private String estadoNuevo;
    
    /**
     * Comentarios adicionales sobre la acción realizada
     */
    @Column(name = "comentarios", length = 500)
    private String comentarios;

    /**
     * Constructor vacío requerido por JPA
     */
    public HistorialReserva() {
    }

    /**
     * Constructor para crear un registro de historial
     * 
     * @param idReserva ID de la reserva afectada
     * @param idUsuario ID del usuario que realizó la acción
     * @param accion Acción realizada
     * @param estadoAnterior Estado anterior (puede ser null para creación)
     * @param estadoNuevo Estado nuevo
     */
    public HistorialReserva(Integer idReserva, Integer idUsuario, String accion, 
                           String estadoAnterior, String estadoNuevo) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.accion = accion;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.fechaHora = LocalDateTime.now();
    }

    // Getters y Setters con documentación

    /**
     * Obtiene el ID único del registro de historial
     * @return ID del historial
     */
    public Integer getIdHistorial() {
        return idHistorial;
    }

    /**
     * Establece el ID del historial
     * @param idHistorial ID único del historial
     */
    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    /**
     * Obtiene el ID de la reserva afectada
     * @return ID de la reserva
     */
    public Integer getIdReserva() {
        return idReserva;
    }

    /**
     * Establece el ID de la reserva
     * @param idReserva ID de la reserva afectada
     */
    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Obtiene el ID del usuario que realizó la acción
     * @return ID del usuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario
     * @param idUsuario ID del usuario que realizó la acción
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene la fecha y hora de la acción
     * @return Momento de la acción
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la acción
     * @param fechaHora Momento de la acción
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene la acción realizada
     * @return Acción (CREADA, CONFIRMADA, CANCELADA, etc.)
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Establece la acción realizada
     * @param accion Tipo de acción realizada
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * Obtiene el estado anterior de la reserva
     * @return Estado anterior o null si es creación
     */
    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    /**
     * Establece el estado anterior
     * @param estadoAnterior Estado previo de la reserva
     */
    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    /**
     * Obtiene el estado nuevo de la reserva
     * @return Estado nuevo de la reserva
     */
    public String getEstadoNuevo() {
        return estadoNuevo;
    }

    /**
     * Establece el estado nuevo
     * @param estadoNuevo Estado actual de la reserva
     */
    public void setEstadoNuevo(String estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    /**
     * Obtiene los comentarios adicionales
     * @return Comentarios sobre la acción
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Establece comentarios adicionales
     * @param comentarios Información adicional sobre la acción
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Método de utilidad para crear un registro de creación de reserva
     * 
     * @param idReserva ID de la reserva creada
     * @param idUsuario ID del usuario que creó la reserva
     * @param estadoInicial Estado inicial de la reserva
     * @return Registro de historial para creación
     */
    public static HistorialReserva crearRegistroCreacion(Integer idReserva, Integer idUsuario, String estadoInicial) {
        return new HistorialReserva(idReserva, idUsuario, "CREADA", null, estadoInicial);
    }

    /**
     * Método de utilidad para crear un registro de cambio de estado
     * 
     * @param idReserva ID de la reserva modificada
     * @param idUsuario ID del usuario que realizó el cambio
     * @param estadoAnterior Estado anterior de la reserva
     * @param estadoNuevo Estado nuevo de la reserva
     * @return Registro de historial para cambio de estado
     */
    public static HistorialReserva crearRegistroCambioEstado(Integer idReserva, Integer idUsuario, 
                                                           String estadoAnterior, String estadoNuevo) {
        return new HistorialReserva(idReserva, idUsuario, "CAMBIO_ESTADO", estadoAnterior, estadoNuevo);
    }

    /**
     * Verifica si este registro representa una cancelación
     * @return true si la acción fue una cancelación
     */
    public boolean esCancelacion() {
        return "CANCELADA".equals(estadoNuevo) || "CANCELACION".equals(accion);
    }

    /**
     * Verifica si este registro representa una confirmación
     * @return true si la acción fue una confirmación
     */
    public boolean esConfirmacion() {
        return "CONFIRMADA".equals(estadoNuevo) || "CONFIRMACION".equals(accion);
    }

    @Override
    public String toString() {
        return "HistorialReserva{" +
                "idHistorial=" + idHistorial +
                ", idReserva=" + idReserva +
                ", idUsuario=" + idUsuario +
                ", fechaHora=" + fechaHora +
                ", accion='" + accion + '\'' +
                ", estadoAnterior='" + estadoAnterior + '\'' +
                ", estadoNuevo='" + estadoNuevo + '\'' +
                '}';
    }
}
