package com.mycompany.proyecto1ssoo;

import java.util.Random;

public class Process{
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
    private ProcessState state;

    public Process(String name, int instructions, boolean isCpuBound, int exceptionCycles, int satisfactionCycles) {
        this.id =  generateRandomId();
        this.name = name;
        this.instructions = instructions;
        this.isCpuBound = isCpuBound;
        this.exceptionCycles = exceptionCycles;
        this.satisfactionCycles = satisfactionCycles;
        this.programCounter = 1;
        this.mar = 0;
        this.state = ProcessState.READY;
         this.arrivalOrder = ++orderCounter;
    }
    
    
    private int generateRandomId() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); 
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public int getInstructions() { return instructions; }
    public int getRemainingInstructions() { return instructions; }
    public boolean isCpuBound() { return isCpuBound; }
    public int getExceptionCycles() { return exceptionCycles; }
    public int getSatisfactionCycles() { return satisfactionCycles; }
    public int getProgramCounter() { return programCounter; }
    public int getMAR() { return mar; }
    public int getArrivalOrder() { return arrivalOrder; }
    public ProcessState getState() { return state; }
    public void setState(ProcessState state) { this.state = state; }
    public void incrementProgramCounter() { this.programCounter++; }

    public boolean hasFinished() {
        return programCounter >= instructions;
    }

}
