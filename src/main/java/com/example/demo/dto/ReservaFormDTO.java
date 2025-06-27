package com.example.demo.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class ReservaFormDTO {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;
    private int idFranja;
    private int numeroPersonas;
    private int idTipoMesa;
    private String comentarios;
    
    public LocalDate getFecha() {return fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}
    public int getIdFranja() {return idFranja;}
    public void setIdFranja(int idFranja) {this.idFranja = idFranja;}
    public int getNumeroPersonas() {return numeroPersonas;}
    public void setNumeroPersonas(int numeroPersonas) {this.numeroPersonas = numeroPersonas;}
    public int getIdTipoMesa() {return idTipoMesa;}
    public void setIdTipoMesa(int idTipoMesa) {this.idTipoMesa = idTipoMesa;}
    public String getComentarios() {return comentarios;}
    public void setComentarios(String comentarios) {this.comentarios = comentarios;}
}