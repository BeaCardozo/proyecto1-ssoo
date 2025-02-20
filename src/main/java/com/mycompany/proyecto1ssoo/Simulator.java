package com.mycompany.proyecto1ssoo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import igu.MainView;
import java.awt.Color;
/**
 *
 * @author Rodrigo
 */
public class Simulator {
    public ProcessQueue readyQueue;
    private ProcessQueue blockedQueue;
    private ProcessQueue finishedQueue;
    private ProcessQueue generalQueue;
    private Processor[] processors;
    private int globalCycle;
    private SchedulingPolicy schedulingPolicy;
    private int numProcessors; 
    private MainView mainView;
    private static Simulator instance;
    private int cycleDuration;

    public Simulator(int numProcessors,MainView mainView) {
        this.numProcessors = numProcessors;  
        this.cycleDuration = cycleDuration;
        this.readyQueue = new ProcessQueue(100);
        this.blockedQueue = new ProcessQueue(100);
        this.finishedQueue = new ProcessQueue(100);
        this.generalQueue = new ProcessQueue(100);
        this.processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new Processor(i);
        }
        this.globalCycle = 0;
        this.mainView = mainView;
        this.instance = instance;
    }
    
    public static Simulator getInstance(int numProcessors, MainView mainView) {
        if (instance == null) {
            instance = new Simulator(numProcessors,mainView); // Pasar mainView al constructor
        }
        return instance;
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
    
    public void reset() {
        this.readyQueue.clear(); 
        this.blockedQueue.clear();
        this.finishedQueue.clear();
        this.generalQueue.clear();
        for (int i = 0; i < numProcessors; i++) {
            processors[i].reset(); 
        }
        this.globalCycle = 0;
        this.schedulingPolicy = null; 
    }
    
   public void classifyProcesses() {
    // No limpiar las colas aquí, solo reclasificar los procesos
    for (int i = 0; i < generalQueue.size(); i++) {
        Process process = generalQueue.get(i);

        switch (process.getState()) {
            case"READY":
                if (!readyQueue.contains(process)) {
                    readyQueue.add(process); // Agregar a la cola de listos si no está ya
                    blockedQueue.remove(process);
                }
                break;
            case "BLOCKED":
                if (!blockedQueue.contains(process)) {
                    blockedQueue.add(process); // Agregar a la cola de bloqueados si no está ya
                    readyQueue.remove(process);
                }
                break;
            case "FINISHED":
                if (!finishedQueue.contains(process)) {
                    finishedQueue.add(process); // Agregar a la cola de finalizados si no está ya
                    blockedQueue.remove(process);
                    readyQueue.remove(process);
                }
                break;
            case "RUNNING":
                // No hacer nada, el proceso ya está en ejecución
                readyQueue.remove(process);
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
    
        // Asignar procesos a los procesadores ociosos
    for (int i =0;i<processors.length; i++) {
        if (processors[i].isIdle()&&!readyQueue.isEmpty()) {
            Process process = readyQueue.remove(); // Eliminar de la cola de listos
            processors[i].assignProcess(process); // Asignar al procesador
        }
    }
    
    for (int i=0;i<processors.length;i++) {
        if (!processors[i].isIdle()) {
            Process process = processors[i].getCurrentProcess();
            // Verificar si el proceso ha terminado
            if (process.hasFinished()) {
                processors[i].releaseProcessor(); // Liberar el procesador
                process.setState("FINISHED");  
                break;
            }
            else if (process.isBlocked() && processors[i].cycles_blocked != process.getSatisfactionCycles()&&!("READY").equals(process.getState())){ 
                process.setState("BLOCKED");
                processors[i].cycles_blocked++;
                MainView.ExecutionModeLabel.setForeground(Color.RED);
                MainView.ExecutionModeLabel.setText("Operative System");
            }else if(process.isBlocked() && processors[i].cycles_blocked ==process.getSatisfactionCycles()){
                processors[i].cycles_blocked =0;
                process.setState("READY");
                processors[i].releaseProcessor();
                break;
            }
            else if("READY".equals(process.getState())||"RUNNING".equals(process.getState())){
                process.setState("RUNNING");
                process.incrementMAR();
                if (process.getMAR() < process.getInstructions()) {
                    process.incrementProgramCounter();
                } 
                // Set remaining instructions
                int remaining = process.getInstructions() - process.getMAR();
                process.setRemainingInstructions(remaining);
            }                      
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
    }
    
    public ProcessQueue getBlockedQueue() { return blockedQueue; }
    public ProcessQueue getFinishedQueue() { return finishedQueue; }
    public ProcessQueue getGeneralQueue() { return generalQueue; }
    
    public int getCycleDuration (){
        return cycleDuration;
    }
    
    public void setCycleDuration (int cycleDuration){
        cycleDuration = cycleDuration;
    }
}