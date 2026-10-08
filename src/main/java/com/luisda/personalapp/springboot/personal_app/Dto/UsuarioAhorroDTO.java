package com.Cesde.concesionario.Dto;

import java.time.LocalDate;

/** Datos de un usuario junto con uno de sus ahorros. */
public class UsuarioAhorroDTO {
    private final String idUsuario;
    private final String nombreUsuario;
    private final String idContabilidad;
    private final String nombreContabilidad;
    private final String idAhorro;
    private final String descripcion;
    private final double valor;
    private final LocalDate fecha;

    public UsuarioAhorroDTO(String idUsuario, String nombreUsuario, String idContabilidad,
                            String nombreContabilidad, String idAhorro, String descripcion,
                            double valor, LocalDate fecha) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idContabilidad = idContabilidad;
        this.nombreContabilidad = nombreContabilidad;
        this.idAhorro = idAhorro;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public String getIdUsuario() { return idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getIdContabilidad() { return idContabilidad; }
    public String getNombreContabilidad() { return nombreContabilidad; }
    public String getIdAhorro() { return idAhorro; }
    public String getDescripcion() { return descripcion; }
    public double getValor() { return valor; }
    public LocalDate getFecha() { return fecha; }
}
