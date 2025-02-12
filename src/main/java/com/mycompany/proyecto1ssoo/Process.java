package com.mycompany.proyecto1ssoo;

import java.util.Random;

public class Process {
    private int id;
    private String name;
    private int instructions;
    private boolean isCpuBound;
    private int exceptionCycles;
    private int satisfactionCycles;
    private int programCounter;
    private int mar;
    private ProcessState state;

    public Process(int id, String name, int instructions, boolean isCpuBound, int exceptionCycles, int satisfactionCycles) {
        this.id =  generateRandomId();
        this.name = name;
        this.instructions = instructions;
        this.isCpuBound = isCpuBound;
        this.exceptionCycles = exceptionCycles;
        this.satisfactionCycles = satisfactionCycles;
        this.programCounter = 1;
        this.mar = 0;
        this.state = ProcessState.READY;
    }
    
    
    private int generateRandomId() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); 
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getInstructions() { return instructions; }
    public boolean isCpuBound() { return isCpuBound; }
    public int getExceptionCycles() { return exceptionCycles; }
    public int getSatisfactionCycles() { return satisfactionCycles; }
    public int getProgramCounter() { return programCounter; }
    public int getMAR() { return mar; }
    public ProcessState getState() { return state; }

    public void setState(ProcessState state) { this.state = state; }
    public void incrementProgramCounter() { this.programCounter++; }

    public boolean hasFinished() {
        return programCounter >= instructions;
    }
}
