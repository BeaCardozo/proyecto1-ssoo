/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ssoo;
import igu.MainView;

/**
 *
 * @author Rodrigo
 */
public class ManejadorExcepciones extends Thread {
    private Proceso proceso;
    private int ciclosSatisfaccion;
    private MainView mainView;

    public ManejadorExcepciones(Proceso proceso, int ciclosSatisfaccion, MainView mainView) {
        this.proceso = proceso;
        this.ciclosSatisfaccion = ciclosSatisfaccion;
        this.mainView = mainView;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(ciclosSatisfaccion * 1000);
            proceso.setEstado("Ready");
            mainView.actualizarInterfaz(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}