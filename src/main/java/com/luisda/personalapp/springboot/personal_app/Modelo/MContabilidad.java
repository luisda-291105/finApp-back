
package com.Cesde.concesionario.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mContabilidad")
public class MContabilidad {

    // Atributos
    @Id
    @Column(length = 36, nullable = false)
    private String idContabilidad;

    @Column(length = 36, nullable = false)
    private String idUsuario;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private boolean estado;

    @Column(nullable = false)
    private LocalDate fechaCreacion;


    // Relación entre clase contabilidad y usuario
    /*
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonManagedReference
    private MUsuario usuario;
    */


    // Relaciones entre clase contabilidad y gasto
    /*
    @OneToMany(mappedBy = "contabilidad")
    @JsonManagedReference
    private List<MGasto> gastos;
    */


    // Relaciones entre clase contabilidad e ingreso
    /*
    @OneToMany(mappedBy = "contabilidad")
    @JsonManagedReference
    private List<MIngreso> ingresos;
    */


    // Relaciones entre clase contabilidad y ahorro
    /*
    @OneToMany(mappedBy = "contabilidad")
    @JsonManagedReference
    private List<MAhorro> ahorros;
    */


    // Relaciones entre clase contabilidad y otro
    /*
    @OneToMany(mappedBy = "contabilidad")
    @JsonManagedReference
    private List<MOtro> otros;
    */


    // Constructores
    public MContabilidad(
            String idContabilidad,
            String idUsuario,
            String nombre,
            double valor,
            boolean estado,
            LocalDate fechaCreacion) {

        this.idContabilidad = idContabilidad;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.valor = valor;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public MContabilidad() {
    }


    // Encapsulamiento
    public String getIdContabilidad() {
        return idContabilidad;
    }

    public void setIdContabilidad(String idContabilidad) {
        this.idContabilidad = idContabilidad;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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