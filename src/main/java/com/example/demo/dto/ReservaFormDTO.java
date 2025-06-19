package com.example.demo.dto;

/**
 * DTO para el formulario de creación de reservas
 * 
 * Este objeto transporta los datos del formulario web hacia el backend,
 * mantiene la información durante las validaciones y redirecciones
 */
public class ReservaFormDTO {
    
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
    private String comentarios;
    private String fecha; // String para facilitar el binding con HTML forms
    private Integer numeroPersonas;
    private Integer idFranja;
    private Integer idTipoMesa;

    /**
     * Constructor vacío requerido para el framework Spring MVC
     */
    public ReservaFormDTO() {
    }

    /**
     * Constructor con todos los parámetros
     * 
     * @param nombreCliente Nombre completo del cliente
     * @param correoCliente Email del cliente
     * @param telefonoCliente Teléfono de contacto
     * @param comentarios Comentarios adicionales (opcional)
     * @param fecha Fecha de la reserva en formato yyyy-MM-dd
     * @param numeroPersonas Cantidad de personas para la reserva
     * @param idFranja ID de la franja horaria seleccionada
     * @param idTipoMesa ID del tipo de mesa requerido
     */
    public ReservaFormDTO(String nombreCliente, String correoCliente, String telefonoCliente,
            String comentarios, String fecha, Integer numeroPersonas,
            Integer idFranja, Integer idTipoMesa) {
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.comentarios = comentarios;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
        this.idFranja = idFranja;
        this.idTipoMesa = idTipoMesa;
    }

    // Getters y Setters con documentación

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
     * @param telefonoCliente Teléfono de contacto
     */
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    /**
     * Obtiene los comentarios adicionales
     * @return Comentarios o null si no hay
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Establece los comentarios de la reserva
     * @param comentarios Comentarios adicionales (opcional)
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Obtiene la fecha de la reserva como string
     * @return Fecha en formato yyyy-MM-dd
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la reserva
     * @param fecha Fecha en formato yyyy-MM-dd
     */
    public void setFecha(String fecha) {
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
     * @param numeroPersonas Cantidad de personas (1-10)
     */
    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    /**
     * Obtiene el ID de la franja horaria seleccionada
     * @return ID de la franja horaria
     */
    public Integer getIdFranja() {
        return idFranja;
    }

    /**
     * Establece el ID de la franja horaria
     * @param idFranja ID de la franja horaria seleccionada
     */
    public void setIdFranja(Integer idFranja) {
        this.idFranja = idFranja;
    }

    /**
     * Obtiene el ID del tipo de mesa requerido
     * @return ID del tipo de mesa
     */
    public Integer getIdTipoMesa() {
        return idTipoMesa;
    }

    /**
     * Establece el ID del tipo de mesa
     * @param idTipoMesa ID del tipo de mesa requerido
     */
    public void setIdTipoMesa(Integer idTipoMesa) {
        this.idTipoMesa = idTipoMesa;
    }

    /**
     * Valida si todos los campos obligatorios están completos
     * @return true si el formulario está completo, false en caso contrario
     */
    public boolean esValido() {
        return nombreCliente != null && !nombreCliente.trim().isEmpty() &&
               correoCliente != null && !correoCliente.trim().isEmpty() &&
               telefonoCliente != null && !telefonoCliente.trim().isEmpty() &&
               fecha != null && !fecha.trim().isEmpty() &&
               numeroPersonas != null && numeroPersonas > 0 &&
               idFranja != null && idTipoMesa != null;
    }

    /**
     * Verifica si el formulario tiene datos precargados
     * @return true si tiene algunos campos llenos
     */
    public boolean tieneDatosPrecargados() {
        return fecha != null || idFranja != null || numeroPersonas != null;
    }

    @Override
    public String toString() {
        return "ReservaFormDTO{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", correoCliente='" + correoCliente + '\'' +
                ", fecha='" + fecha + '\'' +
                ", numeroPersonas=" + numeroPersonas +
                ", idFranja=" + idFranja +
                ", idTipoMesa=" + idTipoMesa +
                '}';
    }
}