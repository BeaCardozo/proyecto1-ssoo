package com.mycompany.proyecto1ssoo;
import igu.MainView;
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
    public int cycles_blocked;
    //Para estadisticas
    private int handledProcessesCount;
    private long totalTimeUsed; 
    private long startTime; 
    private String[] handledProcessNames; 
    private int maxProcesses;
    private int processIndex;
     private static final int INITIAL_MAX_PROCESSES = 100;

    public Processor(int id) {
        this.id = id;
        this.currentProcess = null;
        this.semaphore = new Semaphore(1);
        this.isIdle = true;
        this.cycles_blocked = 0;
        
        this.handledProcessesCount = 0; 
        this.totalTimeUsed = 0;
        this.maxProcesses = 5;
        this.handledProcessNames = new String[INITIAL_MAX_PROCESSES];
        this.processIndex = 0; 
    }

    public int getId() { return id; }
    public Process getCurrentProcess() { return currentProcess; }
    public boolean isIdle() { return isIdle; }

    public void assignProcess(Process process) {
        try {
            semaphore.acquire(); 
            this.isIdle = false;
            this.currentProcess = process;
            startTime = System.currentTimeMillis();
            Thread processThread = new Thread(process);
            processThread.start();
            String processName = process.getName();
            if (!isProcessNameRegistered(processName)) {
                if (processIndex < handledProcessNames.length) {
                    handledProcessNames[processIndex++] = processName;
                    handledProcessesCount++;
                } else {
                    System.out.println("Limite alcanzado, no se puede añadir más procesos.");
                }
            } else { }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void releaseProcessor() {
        if (currentProcess != null) {
            long endTime = System.currentTimeMillis();
            totalTimeUsed += (endTime - startTime); 
            currentProcess = null;
            semaphore.release();
            this.isIdle = true;
        }
    }
    
    public void reset() {
        this.currentProcess = null;
        this.isIdle = true;
        this.semaphore = new Semaphore(1);
        this.handledProcessesCount = 0;
        this.totalTimeUsed = 0;
        this.processIndex = 0;
        for (int i = 0; i < maxProcesses; i++) {
            handledProcessNames[i] = null;
        }
    }
    
    
    //Para estadisticas
     public double calculateCpuUtilization(int totalGlobalCycle, int cycleDuration) {
        int executionTotalTime = (totalGlobalCycle * cycleDuration) / 1000; //ciclos ejecutados * tiempo de los ciclos / 1000 para que sea en segundos
        return ((totalTimeUsed / 1000)* 100.0) / executionTotalTime; 
    }
    
     public int getHandledProcessesCount (){
        return handledProcessesCount;
    }
     
    public long getTotalTimeUsed() {
        return totalTimeUsed; 
    }
     
    public String getProcessesAsString() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Process Index: " + processIndex);
        for (int i = 0; i < processIndex; i++) {
            sb.append(handledProcessNames[i]);
            System.out.println("Handled Process Name: " + handledProcessNames[i]);
            if (i < processIndex - 1) {
                sb.append(", ");
            }
        }   
        return sb.toString();
    }
    
    private boolean isProcessNameRegistered(String processName) {
        for (int i = 0; i < processIndex; i++) {
            if (handledProcessNames[i] != null && handledProcessNames[i].equals(processName)) {
                return true;
            }
        }
        return false;
    }
}
