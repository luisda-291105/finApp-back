package com.Cesde.concesionario.Dto;

import com.Cesde.concesionario.Modelo.MContacto;

/** Datos de un usuario junto con uno de sus contactos. */
public class UsuarioContactoDTO {
    private final String idUsuario;
    private final String nombreUsuario;
    private final String idContacto;
    private final String nombreContacto;
    private final String telefono;
    private final String correo;
    private final MContacto.Tipo tipo;
    private final String notas;

    public UsuarioContactoDTO(
            String idUsuario,
            String nombreUsuario,
            String idContacto,
            String nombreContacto,
            String telefono,
            String correo,
            MContacto.Tipo tipo,
            String notas) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idContacto = idContacto;
        this.nombreContacto = nombreContacto;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;
        this.notas = notas;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getIdContacto() {
        return idContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public MContacto.Tipo getTipo() {
        return tipo;
    }

    public String getNotas() {
        return notas;
    }
}
