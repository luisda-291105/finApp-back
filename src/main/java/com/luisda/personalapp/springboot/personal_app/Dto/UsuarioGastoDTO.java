package com.Cesde.concesionario.Dto;

import com.Cesde.concesionario.Modelo.MGasto;

import java.time.LocalDate;

/** Datos de un usuario junto con uno de sus gastos. */
public class UsuarioGastoDTO {
    private final String idUsuario;
    private final String nombreUsuario;
    private final String idContabilidad;
    private final String nombreContabilidad;
    private final String idGasto;
    private final MGasto.Categoria categoria;
    private final String descripcion;
    private final double valor;
    private final LocalDate fecha;

    public UsuarioGastoDTO(String idUsuario, String nombreUsuario, String idContabilidad,
                           String nombreContabilidad, String idGasto, MGasto.Categoria categoria,
                           String descripcion, double valor, LocalDate fecha) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idContabilidad = idContabilidad;
        this.nombreContabilidad = nombreContabilidad;
        this.idGasto = idGasto;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public String getIdUsuario() { return idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getIdContabilidad() { return idContabilidad; }
    public String getNombreContabilidad() { return nombreContabilidad; }
    public String getIdGasto() { return idGasto; }
    public MGasto.Categoria getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    public double getValor() { return valor; }
    public LocalDate getFecha() { return fecha; }
}
