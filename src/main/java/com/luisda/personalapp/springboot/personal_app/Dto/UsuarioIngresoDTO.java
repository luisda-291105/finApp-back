package com.Cesde.concesionario.Dto;

import com.Cesde.concesionario.Modelo.MIngreso;

import java.time.LocalDate;

/** Datos de un usuario junto con uno de sus ingresos. */
public class UsuarioIngresoDTO {
    private final String idUsuario;
    private final String nombreUsuario;
    private final String idContabilidad;
    private final String nombreContabilidad;
    private final String idIngreso;
    private final MIngreso.Categoria categoria;
    private final String descripcion;
    private final double valor;
    private final LocalDate fecha;

    public UsuarioIngresoDTO(String idUsuario, String nombreUsuario, String idContabilidad,
                             String nombreContabilidad, String idIngreso, MIngreso.Categoria categoria,
                             String descripcion, double valor, LocalDate fecha) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idContabilidad = idContabilidad;
        this.nombreContabilidad = nombreContabilidad;
        this.idIngreso = idIngreso;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public String getIdUsuario() { return idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getIdContabilidad() { return idContabilidad; }
    public String getNombreContabilidad() { return nombreContabilidad; }
    public String getIdIngreso() { return idIngreso; }
    public MIngreso.Categoria getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    public double getValor() { return valor; }
    public LocalDate getFecha() { return fecha; }
}
