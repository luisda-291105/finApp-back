
package com.Cesde.concesionario.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "mOtro")
public class MOtro {

    // Atributos
    @Id
    @Column(length = 36, nullable = false)
    private String idOtro;

    @Column(length = 36, nullable = false)
    private String idContabilidad;

    @Column(length = 255, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private LocalDate fecha;


    // Relación entre clase otro y contabilidad
    /*
    @ManyToOne
    @JoinColumn(name = "idContabilidad")
    @JsonManagedReference
    private MContabilidad contabilidad;
    */


    // Constructores
    public MOtro(
            String idOtro,
            String idContabilidad,
            String descripcion,
            double valor,
            LocalDate fecha) {

        this.idOtro = idOtro;
        this.idContabilidad = idContabilidad;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public MOtro() {
    }


    // Encapsulamiento
    public String getIdOtro() {
        return idOtro;
    }

    public void setIdOtro(String idOtro) {
        this.idOtro = idOtro;
    }

    public String getIdContabilidad() {
        return idContabilidad;
    }

    public void setIdContabilidad(String idContabilidad) {
        this.idContabilidad = idContabilidad;
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