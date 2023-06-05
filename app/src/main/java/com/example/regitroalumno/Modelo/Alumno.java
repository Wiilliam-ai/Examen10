package com.example.regitroalumno.Modelo;

public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private int nota_a;
    private int nota_b;
    private double promedio;
    public Alumno(){}

    public Alumno(String nombre, String apellido, int nota_a, int nota_b, double promedio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nota_a = nota_a;
        this.nota_b = nota_b;
        this.promedio = promedio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNota_a() {
        return nota_a;
    }

    public void setNota_a(int nota_a) {
        this.nota_a = nota_a;
    }

    public int getNota_b() {
        return nota_b;
    }

    public void setNota_b(int nota_b) {
        this.nota_b = nota_b;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "CODIGO: " + id +"\n"+
                "ALUMNO: "+nombre+" "+apellido+"\n"+
                "NOTA 1: "+nota_a+"\n"+"NOTA 2: "+nota_b+"\n"+
                "PROMEDIO: "+promedio;
    }
}
