package com.mycompany.proyecto1ssoo;

import igu.MainView;
import java.awt.Color;
import java.util.Random;

public class Process implements Runnable{
    private int id;
    private String name;
    private int instructions; //Como rafaga de CPU
    private int remainingInstructions;//rafaga restante, lo que queda para que termine de ejecutarse
    private boolean isCpuBound;
    private int exceptionCycles;
    private int satisfactionCycles;
    private int programCounter;
    private int mar;
    private static int orderCounter = 0; 
    private int arrivalOrder; 
    private String state;
    private Simulator simulator;

    public Process(String name, int instructions, boolean isCpuBound, int exceptionCycles, int satisfactionCycles, Simulator simulator) {
        this.id =  generateRandomId();
        this.name = name;
        this.instructions = instructions;
        this.remainingInstructions = 0;
        this.isCpuBound = isCpuBound;
        this.exceptionCycles = exceptionCycles;
        this.satisfactionCycles = satisfactionCycles;
        this.programCounter = 1;
        this.mar = 0;
        this.state = "READY";
         this.arrivalOrder = ++orderCounter;
        this.simulator = simulator;
    }
    
    @Override
    public void run() {
//            // Simular la ejecución de una instrucción
}

    
    public boolean isBlocked(){
        if (!isCpuBound() && getProgramCounter() % getExceptionCycles() == 0){
            return true;
        }else{
            return false;
        }
    }

    
    private int generateRandomId() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); 
    }
    
    private int getCycleDuration(){
        return simulator.getCycleDuration();
    }
    


    public int getId() { return id; }
    public String getName() { return name; }
    public int getInstructions() { return instructions; }
    public int getRemainingInstructions() { return remainingInstructions; }
    public void setRemainingInstructions(int NumberOfinstructions) {
        this.remainingInstructions = NumberOfinstructions;
    }
    public boolean isCpuBound() { return isCpuBound; }
    public int getExceptionCycles() { return exceptionCycles; }
    public int getSatisfactionCycles() { return satisfactionCycles; }
    public int getProgramCounter() { return programCounter; }
    public int getMAR() { return mar; }
    public int getArrivalOrder() { return arrivalOrder; }
    public String getState() { return state; }
    
    public void setState(String state) {
    this.state = state;
}
    public void incrementProgramCounter() { this.programCounter++; }
    public void incrementMAR() { this.mar++; }

    public boolean hasFinished() {
        return mar >= instructions; //esto deberia ser el MAR
    }

}
