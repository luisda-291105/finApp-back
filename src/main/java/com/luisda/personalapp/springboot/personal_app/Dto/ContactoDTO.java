package com.luisda.personalapp.springboot.personal_app.Dto;

public class ContactoDTO {

    // Atributos
    private String idContacto;
    private String nombre;
    private String telefono;
    private String correo;
    private String tipo;
    private String notas;

    // Constructores
    public ContactoDTO(String idContacto, String nombre, String telefono,
                       String correo, String tipo, String notas) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;
        this.notas = notas;
    }

    public ContactoDTO() {
    }

    // Encapsulamiento
    public String getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(String idContacto) {
        this.idContacto = idContacto;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}