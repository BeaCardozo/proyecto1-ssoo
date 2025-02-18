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

    public Simulator(int numProcessors, MainView mainView) {
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
        this.mainView = mainView;
        this.instance = instance;
    }
    
    public static Simulator getInstance(int numProcessors, MainView mainView) {
        if (instance == null) {
            instance = new Simulator(numProcessors, mainView); // Pasar mainView al constructor
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
                    System.out.println("Proceso "+process.getName()+" agregado a la cola de listos");
                }
                break;
            case "BLOCKED":
                if (!blockedQueue.contains(process)) {
                    blockedQueue.add(process); // Agregar a la cola de bloqueados si no está ya
                    System.out.println("Proceso "+process.getName()+" agregado a la cola de bloqueados");
                }
                break;
            case "FINISHED":
                if (!finishedQueue.contains(process)) {
                    finishedQueue.add(process); // Agregar a la cola de finalizados si no está ya
                    System.out.println("Proceso "+process.getName()+" agregado a la cola de terminados");
                }
                break;
            case "RUNNING":
                // No hacer nada, el proceso ya está en ejecución
                System.out.println("proceso en ejecucion");
                break;
            default:
                System.out.println("Estado desconocido: " + process.getState());
                break;
        }
    }System.out.println("ESTADO DE LAS COLAS:");
       System.out.println("LISTOS: ");
       readyQueue.printQueue();
       System.out.println("BLOQUEADOS");
       blockedQueue.printQueue();
       System.out.println("TERMINADOS");
       finishedQueue.printQueue();
       
}

    
    // Adaptar el método executeCycle para implementar Round Robin
    public void executeCycle() {
    globalCycle++;

    // Ejecutar procesos en los procesadores
    for (Processor processor : processors) {
       if (!processor.isIdle()) {
          Process process = processor.getCurrentProcess();
          process.incrementProgramCounter();

            // Verificar si el proceso ha terminado
//            if (process.hasFinished()) {
//                process.setState("FINISHED");
//                System.out.println("Proceso cambio a FINISHED");
//                finishedQueue.add(process); // Agregar a la cola de finalizados
//                readyQueue.remove(process); // Eliminar de la cola de listos
//                processor.releaseProcessor(); // Liberar el procesador
//               break;
//            }

            // Verificar si el proceo es I/O Bound y genera una excepción
            if (!process.isCpuBound() && process.getProgramCounter() % process.getExceptionCycles() == 0) {
                process.setState("BLOCKED");
                System.out.println("Proceso cambio a BLOCKED");
                processor.releaseProcessor(); // Liberar el procesador

                // Iniciar un hilo para manejar la excepción
                new Thread(() -> handleException(process)).start();
                
            }
       }
     }
    // Asignar procesos a los procesadores ociosos
    for (Processor processor : processors) {
        if (processor.isIdle() && !readyQueue.isEmpty()) {
            Process process = readyQueue.remove(); // Eliminar de la cola de listos
            processor.assignProcess(process); // Asignar al procesador
            process.setState("RUNNING");
            System.out.println("Proceso cambio a running");
        }break;
    }

    // Notificar a MainView para actualizar la interfaz gráfica
    mainView.updatePCBSandQueues();
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
    
        public void handleException(Process process) {
    try {
        // Simular el tiempo de satisfacción de la excepción
        MainView.ExecutionModeLabel.setForeground(Color.RED);
        MainView.ExecutionModeLabel.setText("Kernel");
        Thread.sleep(process.getSatisfactionCycles() * 1000); // Convertir a milisegundos
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Desbloquear el proceso
    process.setState("READY");
    System.out.println("El proceso fue desbloqueado y cambio a READY");
    readyQueue.add(process); // Agregar a la cola de listos
    blockedQueue.remove(process); // Eliminar de la cola de bloqueados
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
}