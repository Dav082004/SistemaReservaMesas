package com.example.demo.dto;

/**
 * DTO (Data Transfer Object) para manejar los datos del formulario de registro.
 * Solo contiene los campos que el usuario debe proporcionar.
 */
public class UsuarioRegistroDTO {

    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String usuario;
    private String contrasena;

    // --- Constructor vacío ---
    public UsuarioRegistroDTO() {
    }

    // --- Getters y Setters ---

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}