package com.mycompany.proyecto1ssoo;

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
        while (!hasFinished()) {
            // Simular la ejecución de una instrucción
            incrementProgramCounter();

            // Verificar si el proceso es I/O Bound y genera una excepción
            if (isCpuBound() && getProgramCounter() % getExceptionCycles() == 0) {
                setState("BLOCKED");
                // Notificar al simulador para manejar la excepción
                simulator.handleException(this);
                break; // Salir del bucle mientras el proceso está bloqueado
            }

            // Simular el tiempo de ejecución
            try {
                Thread.sleep(1000); // 1 segundo por ciclo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Cambiar el estado a Finished cuando el proceso termine
        setState("FINISHED");
        System.out.println("Proceso " + getName() + " ha terminado.");
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
    public String getState() { return state; }
    
    public void setState(String state) {
    this.state = state;
    System.out.println("Proceso " + this.name + " cambió a estado: " + state);
}
    public void incrementProgramCounter() { this.programCounter++; }

    public boolean hasFinished() {
        return programCounter >= instructions;
    }

}
