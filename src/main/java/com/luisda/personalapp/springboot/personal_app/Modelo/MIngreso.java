
package com.Cesde.concesionario.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "mIngreso")
public class MIngreso {

    // Atributos
    @Id
    @Column(length = 36, nullable = false)
    private String idIngreso;

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


    // Relación entre clase ingreso y contabilidad
    /*
    @ManyToOne
    @JoinColumn(name = "idContabilidad")
    @JsonManagedReference
    private MContabilidad contabilidad;
    */


    // Enum
    public enum Categoria {
        SALARIO,
        VENTA,
        REGALO,
        OTRO
    }


    // Constructores
    public MIngreso(
            String idIngreso,
            String idContabilidad,
            Categoria categoria,
            String descripcion,
            double valor,
            LocalDate fecha) {

        this.idIngreso = idIngreso;
        this.idContabilidad = idContabilidad;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public MIngreso() {
    }


    // Encapsulamiento
    public String getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(String idIngreso) {
        this.idIngreso = idIngreso;
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