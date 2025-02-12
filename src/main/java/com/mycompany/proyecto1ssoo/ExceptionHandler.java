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
public class ExceptionHandler extends Thread {
    private Process process;
    private int satisfactionCycles;
    private MainView mainView;

    public ExceptionHandler(Process process, int satisfactionCycles, MainView mainView) {
        this.process = process;
        this.satisfactionCycles = satisfactionCycles;
        this.mainView = mainView;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(satisfactionCycles * 1000);
            process.setState(ProcessState.RUNNING);
            mainView.updateInterface();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}