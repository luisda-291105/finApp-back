package com.Cesde.concesionario.Dto;

import java.time.LocalDate;

/** Datos de un usuario junto con otro movimiento financiero. */
public class UsuarioOtroDTO {
    private final String idUsuario;
    private final String nombreUsuario;
    private final String idContabilidad;
    private final String nombreContabilidad;
    private final String idOtro;
    private final String descripcion;
    private final double valor;
    private final LocalDate fecha;

    public UsuarioOtroDTO(String idUsuario, String nombreUsuario, String idContabilidad,
                          String nombreContabilidad, String idOtro, String descripcion,
                          double valor, LocalDate fecha) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idContabilidad = idContabilidad;
        this.nombreContabilidad = nombreContabilidad;
        this.idOtro = idOtro;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public String getIdUsuario() { return idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getIdContabilidad() { return idContabilidad; }
    public String getNombreContabilidad() { return nombreContabilidad; }
    public String getIdOtro() { return idOtro; }
    public String getDescripcion() { return descripcion; }
    public double getValor() { return valor; }
    public LocalDate getFecha() { return fecha; }
}
