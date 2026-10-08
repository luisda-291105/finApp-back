package com.luisda.personalapp.springboot.personal_app.Dto;


import java.time.LocalDate;

public class GastoDTO {

    // Atributos
    private String idGasto;
    private String categoria;
    private String descripcion;
    private double valor;
    private LocalDate fecha;

    // Constructores
    public GastoDTO(String idGasto, String categoria, String descripcion,
                    double valor, LocalDate fecha) {
        this.idGasto = idGasto;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public GastoDTO() {
    }

    // Encapsulamiento
    public String getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(String idGasto) {
        this.idGasto = idGasto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}