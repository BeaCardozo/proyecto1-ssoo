package com.mycompany.proyecto1ssoo;

/**
 *
 * @author Rodrigo
 */
public class Simulator {
    private ProcessQueue readyQueue;
    private ProcessQueue blockedQueue;
    private Processor[] processors;
    private int globalCycle;
    private String schedulingPolicy;
    private int numProcessors; 

    public Simulator(int numProcessors) {
        this.numProcessors = numProcessors;  
        this.readyQueue = new ProcessQueue(100);
        this.blockedQueue = new ProcessQueue(100);
        this.processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new Processor(i);
        }
        this.globalCycle = 0;
        this.schedulingPolicy = "FCFS";  //Arreglar esto a dato dinámico
    }

    // Método para cambiar el número de procesadores
    public void setNumProcessors(int numProcessors) {
        this.numProcessors = numProcessors; 
    }
    
    public int getNumProcessors() {
        return numProcessors; 
    }

    public void addProcess(Process process) {
        readyQueue.add(process);
    }

    public void executeCycle() {
        globalCycle++;
        assignProcesses();
        for (Processor processor : processors) {
            if (!processor.isIdle()) {
                processor.executeCycle();
            }
        }
    }

    private void assignProcesses() {
        for (Processor processor : processors) {
            if (processor.isIdle() && !readyQueue.isEmpty()) {
                Process process = readyQueue.remove();
                processor.assignProcess(process);
            }
        }
    }

    public void changeSchedulingPolicy(String newPolicy) {
        this.schedulingPolicy = newPolicy;
    }

    public int getGlobalCycle() { return globalCycle; }
    public String getSchedulingPolicy() { return schedulingPolicy; }
    public ProcessQueue getReadyQueue() { return readyQueue; }
    public ProcessQueue getBlockedQueue() { return blockedQueue; }
}
