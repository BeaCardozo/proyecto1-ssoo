/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ssoo;

public class Proceso {
    private int id; 
    private String nombre; 
    private int instrucciones; 
    private boolean isCpuBound; 
    private int ciclosExcepcion; 
    private int ciclosSatisfaccion;
    private int programCounter; 
    private String estado; 

    public Proceso(int id, String nombre, int instrucciones, boolean isCpuBound, int ciclosExcepcion, int ciclosSatisfaccion) {
        this.id = id;
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.isCpuBound = isCpuBound;
        this.ciclosExcepcion = ciclosExcepcion;
        this.ciclosSatisfaccion = ciclosSatisfaccion;
        this.programCounter = 0;
        this.estado = "Ready";
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getInstrucciones() { return instrucciones; }
    public boolean isCpuBound() { return isCpuBound; }
    public int getCiclosExcepcion() { return ciclosExcepcion; }
    public int getCiclosSatisfaccion() { return ciclosSatisfaccion; }
    public int getProgramCounter() { return programCounter; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
    public void incrementarProgramCounter() { this.programCounter++; }

    public boolean haTerminado() {
        return programCounter >= instrucciones;
    }
}
