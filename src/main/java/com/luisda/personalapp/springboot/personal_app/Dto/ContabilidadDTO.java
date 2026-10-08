package com.luisda.personalapp.springboot.personal_app.Dto;

import java.time.LocalDate;

public class ContabilidadDTO {

    // Atributos
    private String idContabilidad;
    private String nombre;
    private double valor;
    private boolean estado;
    private LocalDate fechaCreacion;

    // Constructores
    public ContabilidadDTO(String idContabilidad, String nombre,
                           double valor, boolean estado,
                           LocalDate fechaCreacion) {
        this.idContabilidad = idContabilidad;
        this.nombre = nombre;
        this.valor = valor;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public ContabilidadDTO() {
    }

    // Encapsulamiento
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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