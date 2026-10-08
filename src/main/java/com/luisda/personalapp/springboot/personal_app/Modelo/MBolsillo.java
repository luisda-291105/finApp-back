package com.luisda.personalapp.springboot.personal_app.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "MBolsillo")
public class MBolsillo {

    @Id
    @Column(length = 36, nullable = false)
    private String idBolsillo;

    @Column(length = 36, nullable = false)
    private String idContabilidad;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false)
    private boolean estado;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    public enum Tipo {
        GASTO,
        INGRESO,
        AHORRO,
        OTRO
    }

    public MBolsillo() {
    }

    public MBolsillo(String idBolsillo, String idContabilidad, String nombre, Tipo tipo,
                     boolean estado, LocalDate fechaCreacion) {
        this.idBolsillo = idBolsillo;
        this.idContabilidad = idContabilidad;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public String getIdBolsillo() {
        return idBolsillo;
    }

    public void setIdBolsillo(String idBolsillo) {
        this.idBolsillo = idBolsillo;
    }

    public String getIdContabilidad() {
        return idContabilidad;
    }

    public void setIdContabilidad(String idContabilidad) {
        this.idContabilidad = idContabilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
