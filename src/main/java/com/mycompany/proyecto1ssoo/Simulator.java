package com.mycompany.proyecto1ssoo;

import javax.swing.table.DefaultTableModel;
import igu.MainView;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
/**
 *
 * @author Rodrigo
 */
public class Simulator {
    public ProcessQueue readyQueue;
    private ProcessQueue blockedQueue;
    private ProcessQueue finishedQueue;
    private ProcessQueue generalQueue;
    public Processor[] processors;
    private int globalCycle; 
    private int totalGlobalCycle;
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
    
    public void setNumProcessors(int numProcessors) {
        this.numProcessors = numProcessors; 
    }
    
    public int getNumProcessors() {
        return numProcessors; 
    }
    
    public void adjustListSize(int n) {
    if (n <= 3) {
        Processor[] newProcessors = new Processor[n];
        for (int i = 0; i < n; i++) {
            if (i < processors.length) {
                if(processors[processors.length-1].getCurrentProcess()!=null){
                    processors[processors.length-1].getCurrentProcess().setState("READY");
                }
                newProcessors[i] = processors[i];
            } else {
                newProcessors[i] = new Processor(i);
            }
        }
        processors = newProcessors;
    }
}
    
    
    
//    public void changeProcessorsQuantity(int n){
//        int actualQuantity = processors.length;
//        System.out.println("Cantidad de procesadores: "+actualQuantity);
//        System.out.println("Nueva cantidad: "+n);
//        if(actualQuantity!=n){
//            if(n==3){
//                //agregar un procesador
//               this.processors.remove(this.processors.length);
//                
//            }else{
//                //eliminar un procesador
//            }
//        }
//    }
    
            // Asignar procesos a los procesadores ociosos
        
        /*for (int n =0;n<processors.length; n++) {
            if (processors[n].isIdle()&&!readyQueue.isEmpty()) {
                Process process = readyQueue.remove();
                processors[n].assignProcess(process); 
            }
        }*/

    public void addProcess(Process process) {
        generalQueue.add(process);
    }
    
    public void setTotalGlobalCycle() {
        this.totalGlobalCycle = globalCycle; //Settear al final para tener los ciclos totales de la simulación
    }
    
    public int getTotalGlobalCycle (){
        return totalGlobalCycle;
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
        this.totalGlobalCycle = 0;
        this.schedulingPolicy = null; 
    }
    
   public void classifyProcesses() {
    // No limpiar las colas aquí, solo reclasificar los procesos
        for (int i = 0; i < generalQueue.size(); i++) {
            Process process = generalQueue.get(i);

            switch (process.getState()) {
                case"READY":
                    if (!readyQueue.contains(process)) {
                        readyQueue.add(process);
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
       //Simulacion Finalizada
       if (readyQueue.isEmpty() && blockedQueue.isEmpty() && finishedQueue.size() == generalQueue.size()) {
            mainView.GlobalClockSimulationLabel.setText("Global Clock Cycle Number: 0");
            mainView.timer.stop();  
            mainView.isSimulationActive = false;  
            mainView.isSimulationFinished = true;
            setTotalGlobalCycle();
            JOptionPane.showMessageDialog(mainView, "Simulation is Over!", "Done", JOptionPane.INFORMATION_MESSAGE);
            updateIndividualCPUTable();
            double executionTotalTime = getExecutionTotalTime(getTotalGlobalCycle(), getCycleDuration());
            mainView.totalExecutionTimeLabel.setText(Double.toString(executionTotalTime) + " s");
            mainView.totalClockCyclesLabel.setText(getTotalGlobalCycle() + " cycles");
            int finishedCount = getFinishedQueue().size(); 
            int generalCount = getGeneralQueue().size(); 
            if (generalCount == 0) {
                mainView.completionRateLabel.setText("0.0%"); 
            } else {
                double completionRate = ((double) finishedCount / generalCount) * 100; 
                mainView.completionRateLabel.setText(String.format("%.2f%%", completionRate)); 
            } 
            mainView.MetricPlanningPolicyLabel.setText(getSchedulingPolicy().toString());
            mainView.nProcessorsLabel.setText(getNumProcessors() + " processors");
            mainView.processesFinishedLabel.setText(getFinishedQueue().size() + " processes");
            mainView.processesEnteredLabel.setText(getGeneralQueue().size() + " processes");
            mainView.updatePanelVisibility(mainView.isSimulationFinished);
            mainView.StartSimulationButton.setText("Reset Simulator");
            mainView.StartSimulationButton.setBackground(Color.YELLOW);
            mainView.StartSimulationButton.setForeground(Color.BLACK);
        }
        globalCycle++;
        int quantum = 5; 

        
        for (int i = 0; i < processors.length; i++) {
            if (processors[i].isIdle() && !readyQueue.isEmpty()) {
                Process process;
                if (schedulingPolicy == SchedulingPolicy.SPN) {
                    System.out.println("Se está usando la política de SPN.");
                    process = getShortestProcess(readyQueue);
                } else if (schedulingPolicy == SchedulingPolicy.SRT) { 
                    System.out.println("Se está usando la política de SRT.");
                    process = getShortestRemainingTimeProcess(readyQueue);
                }// else if (schedulingPolicy == SchedulingPolicy.FCFS) {
//                    System.out.println("Se está usando la política de FCFS.");
//                    process = getProcessFCFS(readyQueue);
//                } 
                else if (schedulingPolicy == SchedulingPolicy.HRRN) {
                    System.out.println("Se está usando la política de HRRN.");
                    process = getProcessHRRN(readyQueue, globalCycle);
                } 
                else {
                    process = readyQueue.remove();
                }
                processors[i].assignProcess(process);
            }
        }
        for (int n=0;n<processors.length;n++) {
            if (!processors[n].isIdle()) {
                Process process = processors[n].getCurrentProcess();
                if (process.hasFinished()) {
                    processors[n].releaseProcessor(); // Liberar el procesador
                    process.setState("FINISHED"); 
                    break;
                }
                else if (process.isBlocked() && processors[n].cycles_blocked != process.getSatisfactionCycles()&&!("READY").equals(process.getState())){ 
                    process.setState("BLOCKED");
                    processors[n].cycles_blocked++;
                    MainView.ExecutionModeLabel.setForeground(Color.RED);
                    MainView.ExecutionModeLabel.setText("Operative System - Interruption Handler");
                }else if(process.isBlocked() && processors[n].cycles_blocked == process.getSatisfactionCycles()){
                    processors[n].cycles_blocked = 0;
                    process.setState("READY");
                    processors[n].releaseProcessor();
                    break;
                }
               
                if("READY".equals(process.getState())||"RUNNING".equals(process.getState())){
                    process.setState("RUNNING");
                    process.incrementMAR();
                    if (process.getMAR() < process.getInstructions()) {
                        process.incrementProgramCounter();
                    }
 
                //PARA ROUND ROBIN -> MEDIR QUANTUM
                if (schedulingPolicy == SchedulingPolicy.RR) {
                    process.incrementExecuteCycles(); 
                    if (process.getExecuteCycles() >= quantum) {
                        MainView.ExecutionModeLabel.setForeground(Color.RED);
                        MainView.ExecutionModeLabel.setText("Operative System - Timeout");
                        processors[n].releaseProcessor();
                        process.setState("READY");
                        process.resetExecuteCycles(); 
                        readyQueue.add(process); 
                        break;
                    }
                }

            MainView.ExecutionModeLabel.setForeground(Color.GREEN);
                    MainView.ExecutionModeLabel.setText("User");
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
    
     
    //SPN
    private Process getShortestProcess(ProcessQueue queue) {
        Process shortestProcess = null;
        for (Process p : queue.getProcesses()) {
            if (p != null && (shortestProcess == null || p.getInstructions() < shortestProcess.getInstructions())) {
                shortestProcess = p; // Actualiza el proceso más corto
            }
        }
        if (shortestProcess != null) {
        queue.remove(shortestProcess); 
        }
        return shortestProcess; 
    }

    //SRT
    private Process getShortestRemainingTimeProcess(ProcessQueue queue) {
        Process shortestProcess = null;
        for (Process p : queue.getProcesses()) {
            if (p != null && (shortestProcess == null || p.getRemainingInstructions() < shortestProcess.getRemainingInstructions())) {
                shortestProcess = p;
            }
        }
        if (shortestProcess != null) {
            queue.remove(shortestProcess);
        }   
        return shortestProcess; 
    }
    
    
    //FCFS
//    private Process getProcessFCFS(ProcessQueue readyQueue) {
//        Process selectedProcess = null;
//        int minArrivalOrder = Integer.MAX_VALUE; // Inicializamos con el máximo valor posible
//        for (int j = readyQueue.getFront(); j <= readyQueue.getEnd(); j++) {
//            if (readyQueue.getProcesses()[j] != null && readyQueue.getProcesses()[j].getArrivalOrder() < minArrivalOrder) {
//                minArrivalOrder = readyQueue.getProcesses()[j].getArrivalOrder(); // Establece un nuevo mínimo
//                selectedProcess = readyQueue.getProcesses()[j]; // Guarda el proceso seleccionado
//            }
//        }
//        if (selectedProcess != null) {
//            readyQueue.remove(selectedProcess);
//        }
//        return selectedProcess; 
//    }
    


    //HRRN
    private Process getProcessHRRN(ProcessQueue readyQueue, int currentTime) {
        Process selectedProcess = null;
        double maxResponseRatio = Double.MIN_VALUE; 
        for (int j = readyQueue.getFront(); j <= readyQueue.getEnd(); j++) {
            Process currentProcess = readyQueue.getProcesses()[j];
            if (currentProcess != null) {
                int waitingTime = currentTime - currentProcess.getArrivalOrder();
                int burstTime = currentProcess.getInstructions(); 
                if (burstTime > 0) {
                    double responseRatio = (waitingTime + burstTime) / (double) burstTime;
                    if (responseRatio > maxResponseRatio) {
                        maxResponseRatio = responseRatio;
                        selectedProcess = currentProcess; 
                }
            }
        }
    }
        if (selectedProcess != null) {
            readyQueue.remove(selectedProcess);
        }
        return selectedProcess; 
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
    
    public int getGlobalCycle() { return globalCycle; }
    public void setGlobalCycle(int newCycle) {
        this.globalCycle = newCycle; 
    }
    
    public void setSchedulingPolicy(SchedulingPolicy newPolicy) {
        schedulingPolicy = newPolicy; 
    }
    
    public SchedulingPolicy getSchedulingPolicy() {
        return schedulingPolicy;
    }
    
     public void setReadyQueue(ProcessQueue newQueue) {
        readyQueue = newQueue; 
    }

    public ProcessQueue getReadyQueue() {
        return readyQueue; 
    }
    
    public ProcessQueue getBlockedQueue() { return blockedQueue; }
    public ProcessQueue getFinishedQueue() { return finishedQueue; }
    public ProcessQueue getGeneralQueue() { return generalQueue; }
    
    public int getCycleDuration (){
        return cycleDuration;
    }
    
    public void setCycleDuration (int cycleDurationTime){
        cycleDuration = cycleDurationTime;
    }
    
    
    public void updateIndividualCPUTable() {
        DefaultTableModel model = (DefaultTableModel) mainView.IndividualCPUTable.getModel();
        model.setRowCount(0); 
        for (Processor processor : processors) {
            String name = "Processor " + (processor.getId() + 1);  
            long totalTime = processor.getTotalTimeUsed() / 1000;
            String totalTimeUsed = totalTime+ " s";
            double cpuUtilization = processor.calculateCpuUtilization(getTotalGlobalCycle(), getCycleDuration()); 
            DecimalFormat df = new DecimalFormat("#.00");
            String cpuUtilizationString = df.format(cpuUtilization) + "%";
            int handledProcesses = processor.getHandledProcessesCount(); 
            String processes = processor.getProcessesAsString(); 
            System.out.println(processes);
            model.addRow(new Object[] { name, totalTimeUsed, cpuUtilizationString, handledProcesses, processes });
        }
        mainView.IndividualCPUTable.repaint();
    }
    
    //Para tomar los datos y convertirloes en [][]Object para la grafica
    public Object[][] getProcessorData() {
        int numberOfProcessors = processors.length;
        Object[][] newValues = new Object[numberOfProcessors * 2][3]; 
        int index = 0;
        for (Processor processor : processors) {
            String name = "Processor " + (processor.getId() + 1);
            long totalTime = processor.getTotalTimeUsed() / 1000; 
            double cpuUtilization = processor.calculateCpuUtilization(getTotalGlobalCycle(), getCycleDuration());
            newValues[index][0] = totalTime; 
            newValues[index][1] = "Total Time Used (s)"; 
            newValues[index][2] = name; 
            index++;
            newValues[index][0] = cpuUtilization; 
            newValues[index][1] = "CPU Utilization (%)"; 
            newValues[index][2] = name; 
            index++;
        }
        return newValues;
    }

    
    
    public double getExecutionTotalTime(int totalGlobalCycle, int cycleDuration) {
        int executionTotalTime = (totalGlobalCycle * cycleDuration) / 1000;
        return executionTotalTime; 
    }
}