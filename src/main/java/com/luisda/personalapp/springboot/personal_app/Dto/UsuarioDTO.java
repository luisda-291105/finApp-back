package com.luisda.personalapp.springboot.personal_app.Dto;

import java.time.LocalDate;

public class UsuarioDTO {

    // Atributos
    private String idUsuario;
    private String nombre;
    private String contacto;
    private String propiedad;
    private LocalDate fechaCreacion;

    // Constructores
    public UsuarioDTO(String idUsuario, String nombre, String contacto,
                      String propiedad, LocalDate fechaCreacion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contacto = contacto;
        this.propiedad = propiedad;
        this.fechaCreacion = fechaCreacion;
    }

    public UsuarioDTO() {
    }

    // Encapsulamiento
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}