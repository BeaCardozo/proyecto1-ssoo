package com.mycompany.proyecto1ssoo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class Simulator {
    private ProcessQueue readyQueue;
    private ProcessQueue blockedQueue;
    private ProcessQueue finishedQueue;
    private ProcessQueue generalQueue;
    private Processor[] processors;
    private int globalCycle;
    private String schedulingPolicy;
    private int numProcessors; 

    public Simulator(int numProcessors) {
        this.numProcessors = numProcessors;  
        this.readyQueue = new ProcessQueue(100);
        this.blockedQueue = new ProcessQueue(100);
        this.finishedQueue = new ProcessQueue(100);
        this.generalQueue = new ProcessQueue(100);
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
        generalQueue.add(process);
    }
    
    public void classifyProcesses() {
        int size = generalQueue.size();

        for (int i = 0; i < size; i++) {
            Process process = generalQueue.get(i); 

            switch (process.getState()) {
                case READY:
                    readyQueue.add(process); // Añadir a la cola de listos
                    break;
                case BLOCKED:
                    blockedQueue.add(process); // Añadir a la cola bloqueada
                    break;
                case FINISHED:
                    finishedQueue.add(process); // Añadir a la cola finalizada
                    break;
                case RUNNING:
                    System.out.println("proceso en ejecución");
                    break;
                default:
                    System.out.println("Estado desconocido: " + process.getState());
                break;
            }
        }
    }

    
    // Adaptar el método executeCycle para implementar Round Robin
    public void executeCycle() {
        globalCycle++;
        for (Processor processor : processors) {
            if (processor.isIdle() && !readyQueue.isEmpty()) {
                Process process = readyQueue.remove(); // Obtener el siguiente proceso
                processor.assignProcess(process); // Asignar proceso al procesador
                
                //new Thread(process).start(); // Iniciar el proceso en un hilo
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
        printProcessTable();
    }

    public void changeSchedulingPolicy(String newPolicy) {
        this.schedulingPolicy = newPolicy;
    }
    
    
       //Llenar tabla de procesos:
    public DefaultTableModel updateProcessTable() {
        DefaultTableModel model = new DefaultTableModel();
        for (int i = 0; i < numProcessors; i++) {
            model.addColumn("Processor " + (i + 1));
        }
        Object[] rowData = new Object[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            Processor processor = processors[i]; // 
            Process currentProcess = processor.getCurrentProcess();
            String processName = currentProcess != null ? currentProcess.getName() : "Ninguno";
            rowData[i] = processName;
        }
        model.addRow(rowData);
        return model;
    }
    
    
    public void printProcessTable() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.printf("%-10s %-20s %-10s\n", "Procesador", "Proceso Asignado", "Estado");
        System.out.println("-------------------------------------------");
        for (Processor processor : processors) {
            Process currentProcess = processor.getCurrentProcess();
            String processName = currentProcess != null ? currentProcess.getName() : "Ninguno";
            String processState = currentProcess != null ? currentProcess.getState().toString() : "Ninguno";

            System.out.printf("%-10d %-20s %-10s\n", processor.getId(), processName, processState);
        }
    }
    


    public int getGlobalCycle() { return globalCycle; }
    public void setGlobalCycle(int newCycle) {
        this.globalCycle = newCycle; 
    }
    
    public String getSchedulingPolicy() { return schedulingPolicy; }
    public ProcessQueue getReadyQueue() { return readyQueue; }
    public ProcessQueue getBlockedQueue() { return blockedQueue; }
    public ProcessQueue getFinishedQueue() { return finishedQueue; }
    public ProcessQueue getGeneralQueue() { return generalQueue; }
}
