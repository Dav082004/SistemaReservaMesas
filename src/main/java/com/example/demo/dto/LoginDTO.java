package com.example.demo.dto;

public class LoginDTO {
    private String usuario;
    private String contrasena;

    // Constructores
    public LoginDTO() {
    }

    public LoginDTO(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
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
        return "LoginDTO{" +
                "usuario='" + usuario + '\'' +
                '}';
    }
}
