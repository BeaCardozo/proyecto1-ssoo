/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ssoo;

/**
 *
 * @author Rodrigo
 */
public class Processor {
    private int id;
    private Process currentProcess; 
    private boolean isIdle;

    public Processor(int id) {
        this.id = id;
        this.currentProcess = null;
        this.isIdle = true;
    }

    public int getId() { return id; }
    public Process getCurrentProcess() { return currentProcess; }
    public boolean isIdle() { return isIdle; }

    public void assignProcess(Process process) {
        this.currentProcess = process;
        this.isIdle = false;
        process.setState("Running");
    }

    public void releaseProcessor() {
        this.currentProcess = null;
        this.isIdle = true;
    }

    public void executeCycle() {
        if (currentProcess != null) {
            currentProcess.incrementProgramCounter();
            if (currentProcess.hasFinished()) {
                releaseProcessor();
            }
        }
    }
}



