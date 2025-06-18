package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "nombreCompleto")
    private String nombreCompleto;

    @Column(name = "correo", unique = true, nullable = false)
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "usuario", unique = true, nullable = false)
    private String usuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "rol", nullable = false)
    private String rol;

    // Constructores
    public Usuario() {
    }

    public Usuario(String nombreCompleto, String correo, String telefono, String usuario, String contrasena,
            String rol) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", usuario='" + usuario + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}