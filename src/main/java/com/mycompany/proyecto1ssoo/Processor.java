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
        process.setState(ProcessState.RUNNING);
    }

    public void releaseProcessor() {
        this.currentProcess = null;
        this.isIdle = true;
    }

    public void executeCycle() {
        if (currentProcess != null) {
            currentProcess.incrementProgramCounter();
            if (currentProcess.hasFinished()) {
                currentProcess.setState(ProcessState.FINISHED);
                releaseProcessor();
            }
        }
    }
}



