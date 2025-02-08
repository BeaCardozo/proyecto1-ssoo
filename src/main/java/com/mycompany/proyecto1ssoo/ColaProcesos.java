/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ssoo;

/**
 *
 * @author Rodrigo
 */
public class ColaProcesos {
    private Proceso[] procesos;
    private int frente;
    private int fin;
    private int capacidad;

    // Constructor
    public ColaProcesos(int capacidad) {
        this.capacidad = capacidad;
        this.procesos = new Proceso[capacidad];
        this.frente = 0;
        this.fin = -1;
    }

    public void agregar(Proceso proceso) {
        if (fin < capacidad - 1) {
            fin++;
            procesos[fin] = proceso;
        } else {
            System.out.println("Cola llena, no se puede agregar más procesos.");
        }
    }

    public Proceso eliminar() {
        if (frente > fin) {
            System.out.println("Cola vacía, no hay procesos para eliminar.");
            return null;
        } else {
            Proceso proceso = procesos[frente];
            frente++;
            return proceso;
        }
    }

    public boolean estaVacia() {
        return frente > fin;
    }

    public int tamaño() {
        return fin - frente + 1;
    }

    public Proceso obtener(int indice) {
        if (indice >= 0 && indice < tamaño()) {
            return procesos[frente + indice];
        } else {
            System.out.println("Índice fuera de rango.");
            return null;
        }
    }
}
