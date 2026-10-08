package com.luisda.personalapp.springboot.personal_app.Dto;


import java.time.LocalDate;

public class AhorroDTO {

    // Atributos
    private String idAhorro;
    private String descripcion;
    private double valor;
    private LocalDate fecha;

    // Constructores
    public AhorroDTO(String idAhorro, String descripcion,
                     double valor, LocalDate fecha) {
        this.idAhorro = idAhorro;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public AhorroDTO() {
    }

    // Encapsulamiento
    public String getIdAhorro() {
        return idAhorro;
    }

    public void setIdAhorro(String idAhorro) {
        this.idAhorro = idAhorro;
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