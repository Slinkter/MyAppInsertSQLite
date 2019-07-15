package com.vidrieriachaloreyes.myappcrudsqlite.SQLite;

import java.io.Serializable;

public class Mascota implements Serializable {


    private Integer idMascota;
    private String nombreMascota;
    private String raza;

    private Integer idDueno;

    public Mascota() {

    }

    public Mascota(Integer idDueno, Integer idMascota, String nombreMascota, String raza) {
        this.idDueno = idDueno;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
    }

    public Integer getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(Integer idDueno) {
        this.idDueno = idDueno;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
