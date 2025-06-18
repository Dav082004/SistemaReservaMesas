package com.example.demo.Entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "TipoMesa")
public class TipoMesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoMesa;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "tipoMesa")
    private List<Mesa> mesas = new ArrayList<>();

    // Constructores
    public TipoMesa() {
    }

    public TipoMesa(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Integer getIdTipoMesa() {
        return idTipoMesa;
    }

    public void setIdTipoMesa(Integer idTipoMesa) {
        this.idTipoMesa = idTipoMesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    @Override
    public String toString() {
        return "TipoMesa{" +
                "idTipoMesa=" + idTipoMesa +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
