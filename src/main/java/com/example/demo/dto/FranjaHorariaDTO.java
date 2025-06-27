package com.example.demo.dto;

public class FranjaHorariaDTO {
    private int idFranja;
    private String franjaHoraria;
    private int mesasDisponibles;
    private boolean estaCasiLleno;

    public FranjaHorariaDTO(int idFranja, String franjaHoraria, int mesasDisponibles, boolean estaCasiLleno) {
        this.idFranja = idFranja;
        this.franjaHoraria = franjaHoraria;
        this.mesasDisponibles = mesasDisponibles;
        this.estaCasiLleno = estaCasiLleno;
    }
    public int getIdFranja() {return idFranja;}
    public void setIdFranja(int idFranja) {this.idFranja = idFranja;}
    public String getFranjaHoraria() {return franjaHoraria;}
    public void setFranjaHoraria(String franjaHoraria) {this.franjaHoraria = franjaHoraria;}
    public int getMesasDisponibles() {return mesasDisponibles;}
    public void setMesasDisponibles(int mesasDisponibles) {this.mesasDisponibles = mesasDisponibles;}
    public boolean isEstaCasiLleno() {return estaCasiLleno;}
    public void setEstaCasiLleno(boolean estaCasiLleno) {this.estaCasiLleno = estaCasiLleno;}
}
