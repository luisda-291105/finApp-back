
package com.luisda.personalapp.springboot.personal_app.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

/**
 * @uso               Representa un contacto asociado a un usuario
 * @capa              Modelo
 * @responsabilidades Mantener los datos y el tipo del contacto
 * @datos             Identificador, usuario, nombre, teléfono, correo, tipo y notas
 * @dependencias      JPA
 * @usadoPor          ContactoRepository
 */
@Entity
@Table(name = "MContacto")
public class MContacto {

    // Atributos
    @Id
    @Column(length = 36, nullable = false)
    private String idContacto;

    @Column(length = 36, nullable = false)
    private String idUsuario;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 20, nullable = false)
    private String telefono;

    @Column(length = 150)
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(length = 500)
    private String notas;


    // Relación entre clase contacto y grupo
    /*
    @ManyToOne
    @JoinColumn(name = "idGrupo")
    @JsonManagedReference
    private MGrupo grupo;
    */


    // Enum
    public enum Tipo {
        PERSONAL,
        FAMILIAR,
        AMIGO,
        TRABAJO
    }


    // Constructores
    public MContacto(
            String idContacto,
            String idUsuario,
            String nombre,
            String telefono,
            String correo,
            Tipo tipo,
            String notas) {

        this.idContacto = idContacto;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;
        this.notas = notas;
    }

    public MContacto() {
    }


    // Encapsulamiento
    public String getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(String idContacto) {
        this.idContacto = idContacto;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
