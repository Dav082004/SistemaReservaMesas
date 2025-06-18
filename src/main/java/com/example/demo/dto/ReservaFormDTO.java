package com.example.demo.dto;

public class ReservaFormDTO {
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
    private String comentarios;
    private String fecha;
    private Integer numeroPersonas;
    private Integer idFranja;
    private Integer idTipoMesa;

    // Constructores
    public ReservaFormDTO() {
    }

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

    // Getters y Setters
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public Integer getIdFranja() {
        return idFranja;
    }

    public void setIdFranja(Integer idFranja) {
        this.idFranja = idFranja;
    }

    public Integer getIdTipoMesa() {
        return idTipoMesa;
    }

    public void setIdTipoMesa(Integer idTipoMesa) {
        this.idTipoMesa = idTipoMesa;
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