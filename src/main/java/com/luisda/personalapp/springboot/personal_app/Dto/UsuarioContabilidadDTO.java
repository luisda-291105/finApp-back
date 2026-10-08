package com.Cesde.concesionario.Dto;

import java.time.LocalDate;

/** Datos de un usuario junto con una de sus contabilidades. */
public class UsuarioContabilidadDTO {
    private final String idUsuario;
    private final String nombreUsuario;
    private final String idContabilidad;
    private final String nombreContabilidad;
    private final double valor;
    private final boolean estado;
    private final LocalDate fechaCreacion;

    public UsuarioContabilidadDTO(
            String idUsuario,
            String nombreUsuario,
            String idContabilidad,
            String nombreContabilidad,
            double valor,
            boolean estado,
            LocalDate fechaCreacion) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idContabilidad = idContabilidad;
        this.nombreContabilidad = nombreContabilidad;
        this.valor = valor;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getIdContabilidad() {
        return idContabilidad;
    }

    public String getNombreContabilidad() {
        return nombreContabilidad;
    }

    public double getValor() {
        return valor;
    }

    public boolean isEstado() {
        return estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
}
