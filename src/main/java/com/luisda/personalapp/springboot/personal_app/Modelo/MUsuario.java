
package com.luisda.personalapp.springboot.personal_app.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "MUsuario")
public class MUsuario {

    // Atributos
    @Id
    @Column(length = 36, nullable = false)
    private String idUsuario;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 150, nullable = false, unique = true)
    private String contacto;

    @Column(length = 255, nullable = false)
    private String contrasena;

    @Column(length = 255)
    private String propiedad;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    // Relaciones entre clase usuario y grupo
    /*
    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<MGrupo> grupo;
    */

    // Constructores
    public MUsuario(
            String idUsuario,
            String nombre,
            String contacto,
            String contrasena,
            String propiedad,
            LocalDate fechaCreacion) {

        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contacto = contacto;
        this.contrasena = contrasena;
        this.propiedad = propiedad;
        this.fechaCreacion = fechaCreacion;
    }

    public MUsuario() {
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
