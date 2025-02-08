/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ssoo;

/**
 *
 * @author Rodrigo
 */
public class Simulador {
    private ColaProcesos colaListos; 
    private ColaProcesos colaBloqueados; 
    private Procesador[] procesadores; 
    private int cicloGlobal; 
    private String politicaPlanificacion; 

    public Simulador(int numProcesadores) {
        this.colaListos = new ColaProcesos(100); // Capacidad máxima de 100 procesos
        this.colaBloqueados = new ColaProcesos(100); // Capacidad máxima de 100 procesos
        this.procesadores = new Procesador[numProcesadores];
        for (int i = 0; i < numProcesadores; i++) {
            procesadores[i] = new Procesador(i);
        }
        this.cicloGlobal = 0;
        this.politicaPlanificacion = "FCFS"; // Por defecto, FCFS
    }

    public void agregarProceso(Proceso proceso) {
        colaListos.agregar(proceso);
    }

    public void ejecutarCiclo() {
        cicloGlobal++;
        asignarProcesos();
        for (Procesador procesador : procesadores) {
            if (!procesador.estaOcioso()) {
                procesador.ejecutarCiclo();
            }
        }
    }

    private void asignarProcesos() {
        for (Procesador procesador : procesadores) {
            if (procesador.estaOcioso() && !colaListos.estaVacia()) {
                Proceso proceso = colaListos.eliminar();
                procesador.asignarProceso(proceso);
            }
        }
    }

    public void cambiarPoliticaPlanificacion(String nuevaPolitica) {
        this.politicaPlanificacion = nuevaPolitica;
        // Aquí se podría implementar la lógica para reordenar la cola de listos
    }

    public int getCicloGlobal() { return cicloGlobal; }
    public String getPoliticaPlanificacion() { return politicaPlanificacion; }
    public ColaProcesos getColaListos() { return colaListos; }
    public ColaProcesos getColaBloqueados() { return colaBloqueados; }
}
