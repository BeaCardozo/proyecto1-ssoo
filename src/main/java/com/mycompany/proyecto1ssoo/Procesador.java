/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ssoo;

/**
 *
 * @author Rodrigo
 */
public class Procesador {
    private int id; //
    private Proceso procesoActual; 
    private boolean estaOcioso; 

    public Procesador(int id) {
        this.id = id;
        this.procesoActual = null;
        this.estaOcioso = true;
    }

    public int getId() { return id; }
    public Proceso getProcesoActual() { return procesoActual; }
    public boolean estaOcioso() { return estaOcioso; }

    public void asignarProceso(Proceso proceso) {
        this.procesoActual = proceso;
        this.estaOcioso = false;
        proceso.setEstado("Running");
    }

    public void liberarProcesador() {
        this.procesoActual = null;
        this.estaOcioso = true;
    }

    public void ejecutarCiclo() {
        if (procesoActual != null) {
            procesoActual.incrementarProgramCounter();
            if (procesoActual.haTerminado()) {
                liberarProcesador();
            }
        }
    }
}