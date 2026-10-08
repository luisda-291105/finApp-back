package com.luisda.personalapp.springboot.personal_app.Dto;

import java.time.LocalDate;

public class OtroDTO {

    // Atributos
    private String idOtro;
    private String descripcion;
    private double valor;
    private LocalDate fecha;

    // Constructores
    public OtroDTO(String idOtro, String descripcion,
                   double valor, LocalDate fecha) {
        this.idOtro = idOtro;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public OtroDTO() {
    }

    // Encapsulamiento
    public String getIdOtro() {
        return idOtro;
    }

    public void setIdOtro(String idOtro) {
        this.idOtro = idOtro;
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