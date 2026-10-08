
package com.Cesde.concesionario.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "mGasto")
public class MGasto {

    // Atributos
    @Id
    @Column(length = 36, nullable = false)
    private String idGasto;

    @Column(length = 36, nullable = false)
    private String idContabilidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(length = 255, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private LocalDate fecha;


    // Relación entre clase gasto y contabilidad
    /*
    @ManyToOne
    @JoinColumn(name = "idContabilidad")
    @JsonManagedReference
    private MContabilidad contabilidad;
    */


    // Enum
    public enum Categoria {
        ALIMENTACION,
        TRANSPORTE,
        VIVIENDA,
        SALUD,
        OTRO
    }


    // Constructores
    public MGasto(
            String idGasto,
            String idContabilidad,
            Categoria categoria,
            String descripcion,
            double valor,
            LocalDate fecha) {

        this.idGasto = idGasto;
        this.idContabilidad = idContabilidad;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public MGasto() {
    }


    // Encapsulamiento
    public String getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(String idGasto) {
        this.idGasto = idGasto;
    }

    public String getIdContabilidad() {
        return idContabilidad;
    }

    public void setIdContabilidad(String idContabilidad) {
        this.idContabilidad = idContabilidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
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