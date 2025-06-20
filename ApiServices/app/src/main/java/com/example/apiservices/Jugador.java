package com.example.apiservices;

public class Jugador {
    private String nombre;
    private String posicion;
    private String nacionalidad;
    private String fechaNacimiento;
    private int numeroCamiseta;
    private String equipo;

    public Jugador(String nombre, String posicion, String nacionalidad, String fechaNacimiento, int numeroCamiseta, String equipo) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroCamiseta = numeroCamiseta;
        this.equipo = equipo;
    }

    public String getNombre() { return nombre; }
    public String getPosicion() { return posicion; }
    public String getNacionalidad() { return nacionalidad; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public int getNumeroCamiseta() { return numeroCamiseta; }
    public String getEquipo() { return equipo; }
}
