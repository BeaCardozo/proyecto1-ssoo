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
    private SchedulingPolicy schedulingPolicy;
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
            String processName = currentProcess != null ? currentProcess.getName() : "None";
            rowData[i] = processName;
        }
        model.addRow(rowData);
        return model;
    }
    
    //--------------- IDEA DE FUNCIONES DE PLANIFICACIÓN ----------------------//
    //SPN (Shortest Process Next)
    public ProcessQueue reorderBySPN() {
        int queueSize = readyQueue.size(); 
        ProcessQueue sortedQueue = new ProcessQueue(queueSize); 
        Process[] processes = new Process[queueSize];
        for (int i = 0; i < queueSize; i++) {
            processes[i] = readyQueue.get(i); 
        }
        for (int i = 0; i < queueSize - 1; i++) {
            for (int j = 0; j < queueSize - 1 - i; j++) {
                if (processes[j].getInstructions() > processes[j + 1].getInstructions()) {
                    Process tmp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = tmp;
                }
            }
        }
        for (Process p : processes) {
            sortedQueue.add(p); 
        }
        return sortedQueue; 
    }
    
    
    
    //SRT (Shortest Remaining Time)
    public ProcessQueue reorderBySRT() {
        int queueSize = readyQueue.size();
        ProcessQueue sortedQueue = new ProcessQueue(queueSize);
        Process[] processes = new Process[queueSize];
        for (int i = 0; i < queueSize; i++) {
            processes[i] = readyQueue.get(i);
        }
        for (int i = 0; i < queueSize - 1; i++) {
            for (int j = 0; j < queueSize - 1 - i; j++) {
                if (processes[j].getRemainingInstructions() > processes[j + 1].getRemainingInstructions()) {
                    Process tmp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = tmp;
                }
            }
        }
        for (Process p : processes) {
            sortedQueue.add(p);
        }

        return sortedQueue; 
    }
    
    
    //HRRN (Highest Response Radio Next)
    public ProcessQueue reorderByHRRN() {
        int queueSize = readyQueue.size();
        ProcessQueue sortedQueue = new ProcessQueue(queueSize);
        Process[] processes = new Process[queueSize];
        for (int i = 0; i < queueSize; i++) {
            processes[i] = readyQueue.get(i);
        }
        double[] responseRatios = new double[queueSize];
        int currentTime = globalCycle;
        for (int i = 0; i < queueSize; i++) {
            Process p = processes[i];
            responseRatios[i] = (currentTime - p.getArrivalOrder() + p.getRemainingInstructions()) / (double) p.getRemainingInstructions();
        }
        for (int i = 0; i < queueSize - 1; i++) {
            for (int j = 0; j < queueSize - 1 - i; j++) {
                if (responseRatios[j] < responseRatios[j + 1]) {
                    // Intercambiar procesos
                    Process tmp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = tmp;

                    double tempRatio = responseRatios[j];
                    responseRatios[j] = responseRatios[j + 1];
                    responseRatios[j + 1] = tempRatio;
                }
            }
        }
        for (Process p : processes) {
            sortedQueue.add(p);
        }
        return sortedQueue;
    }
    
    
    //FCFS (First-Come, First-Served)
    public ProcessQueue reorderByFCFS() {
        int queueSize = readyQueue.size();
        ProcessQueue sortedQueue = new ProcessQueue(queueSize);
        //sortedQueue.clear(); 
        Process[] processes = new Process[queueSize];
        for (int i = 0; i < queueSize; i++) {
            processes[i] = readyQueue.get(i);
        }
        for (int i = 0; i < queueSize - 1; i++) {
            for (int j = 0; j < queueSize - 1 - i; j++) {
                if (processes[j].getArrivalOrder() > processes[j + 1].getArrivalOrder()) {
                    Process tmp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = tmp;
                }
            }
        }
        for (Process p : processes) {
            sortedQueue.add(p);
        }
        return sortedQueue; 
    }
    
    
    //Round Robin
    public ProcessQueue reorderByRoundRobin() {
        int queueSize = readyQueue.size();
        ProcessQueue sortedQueue = new ProcessQueue(queueSize);
        //sortedQueue.clear(); 
        Process[] processes = new Process[queueSize];
        
        for (int i = 0; i < queueSize; i++) {
            processes[i] = readyQueue.get(i);
        }
        for (int i = 0; i < queueSize - 1; i++) {
            for (int j = 0; j < queueSize - 1 - i; j++) {
                if (processes[j].getArrivalOrder() > processes[j + 1].getArrivalOrder()) {
                    Process tmp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = tmp;
                }
            }
        }
        for (Process p : processes) {
            sortedQueue.add(p);
        }
        return sortedQueue; 
    }
    
    //----------------------------------------------------------------------//

    public int getGlobalCycle() { return globalCycle; }
    public void setGlobalCycle(int newCycle) {
        this.globalCycle = newCycle; 
    }
    
    public void setSchedulingPolicy(SchedulingPolicy newPolicy) {
        schedulingPolicy = newPolicy; 
    }
    
     public void setReadyQueue(ProcessQueue newQueue) {
        readyQueue = newQueue; 
    }

    public ProcessQueue getReadyQueue() {
        return readyQueue; 
    }

    public void reorderAndSetReadyQueue() {
        ProcessQueue sortedQueue = schedulingPolicy.reorder(this); 
        setReadyQueue(sortedQueue); 
        readyQueue.printQueue();
    }
    
    public ProcessQueue getBlockedQueue() { return blockedQueue; }
    public ProcessQueue getFinishedQueue() { return finishedQueue; }
    public ProcessQueue getGeneralQueue() { return generalQueue; }
}
