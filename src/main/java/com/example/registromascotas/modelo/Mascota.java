package com.example.registromascotas.modelo;

public class Mascota {

    private int id;
    private String codigo;
    private String nombre;
    private String raza;
    private int edad;
    private String propietario;
    private String especie;
    private String sexo;
    private String estadoSalud;
    private String observaciones;

    public Mascota() {
    }
    public Mascota(int id, String codigo, String nombre,
                   String raza, int edad, String propietario,
                   String especie, String sexo,
                   String estadoSalud, String observaciones) {

        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.propietario = propietario;
        this.especie = especie;
        this.sexo = sexo;
        this.estadoSalud = estadoSalud;
        this.observaciones = observaciones;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRaza() {
        return raza;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getPropietario() {
        return propietario;
    }
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getEstadoSalud() {
        return estadoSalud;
    }
    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}