package com.luisda.personalapp.springboot.personal_app.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "MGrupo")
public class MGrupo {
    @Id
    @Column(length = 36 , nullable = false)
    private String idGrupo;
    @Column(length = 36, nullable = false)
    private String idUsuario;
    @Column(length = 100 , nullable = false)
    private String nombre;
    @Column(length = 255 , nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private Boolean estado;
    @Column(nullable = false)
    private LocalDate fechaCreacion;

    public MGrupo(String idGrupo, String nombre, String descripcion, Boolean estado) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public MGrupo(String idGrupo, String idUsuario, String nombre, String descripcion,
                  Boolean estado, LocalDate fechaCreacion) {
        this.idGrupo = idGrupo;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public MGrupo() {
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
