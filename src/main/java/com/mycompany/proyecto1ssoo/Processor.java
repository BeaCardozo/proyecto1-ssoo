package com.mycompany.proyecto1ssoo;
import java.util.concurrent.Semaphore;
/**
 *
 * @author Rodrigo
 */
public class Processor {
    private int id;
    private Process currentProcess; 
    public boolean isIdle;
    private Semaphore semaphore;

    public Processor(int id) {
        this.id = id;
        this.currentProcess = null;
        this.semaphore = new Semaphore(1);
        this.isIdle = true;
    }

    public int getId() { return id; }
    public Process getCurrentProcess() { return currentProcess; }
    public boolean isIdle() { return isIdle; }

    public void assignProcess(Process process) {
        try {
            semaphore.acquire(); // Adquirir el semáforo
            this.isIdle = false;
            this.currentProcess = process;
            //process.setState("RUNNING");

            // Iniciar un hilo para ejecutar el proceso
            Thread processThread = new Thread(process);
            processThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void releaseProcessor() {
        this.currentProcess = null;
        semaphore.release();
        this.isIdle = true;
    }

}
