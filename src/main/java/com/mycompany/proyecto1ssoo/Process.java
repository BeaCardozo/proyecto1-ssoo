package com.mycompany.proyecto1ssoo;

public class Process {
    private int id;
    private String name;
    private int instructions;
    private boolean isCpuBound;
    private int exceptionCycles;
    private int satisfactionCycles;
    private int programCounter;
    private ProcessState state;

    public Process(int id, String name, int instructions, boolean isCpuBound, int exceptionCycles, int satisfactionCycles) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.isCpuBound = isCpuBound;
        this.exceptionCycles = exceptionCycles;
        this.satisfactionCycles = satisfactionCycles;
        this.programCounter = 0;
        this.state = ProcessState.READY;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getInstructions() { return instructions; }
    public boolean isCpuBound() { return isCpuBound; }
    public int getExceptionCycles() { return exceptionCycles; }
    public int getSatisfactionCycles() { return satisfactionCycles; }
    public int getProgramCounter() { return programCounter; }
    public ProcessState getState() { return state; }

    public void setState(ProcessState state) { this.state = state; }
    public void incrementProgramCounter() { this.programCounter++; }

    public boolean hasFinished() {
        return programCounter >= instructions;
    }
}
