package com.example.demo.dto;

public class UsuarioRegistroDto {
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String usuario;
    private String contrasena;

    // Constructores
    public UsuarioRegistroDto() {
    }

    public UsuarioRegistroDto(String nombreCompleto, String correo, String telefono, String usuario,
            String contrasena) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
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

    @Override
    public String toString() {
        return "UsuarioRegistroDto{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
