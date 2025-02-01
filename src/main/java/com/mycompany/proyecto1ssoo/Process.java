/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ssoo;

/**
 *
 * @author beacardozo
 */
import java.util.UUID;

enum ProcessType {
    IO_BOUND("I/O Bound"),
    CPU_BOUND("CPU Bound");

    private final String typeName;

    ProcessType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}

class Process {
    private String id; 
    private String name;
    private ProcessType type; 
    private Integer toGenerateException; //allows null (optional)
    private Integer toSatisfyException; //allows null (optional)

    
    public Process(String name, ProcessType type, Integer toGenerateException, Integer toSatisfyException) {
        this.id = UUID.randomUUID().toString(); 
        this.name = name;
        this.type = type;
        this.toGenerateException = toGenerateException;
        this.toSatisfyException = toSatisfyException;
    }

   //CPU Bound process
    public Process(String name, ProcessType type) {
        this(name, type, null, null); 
    }

    //I/O Bpund process
    public Process(String name, ProcessType type, Integer toGenerateException) {
        this(name, type, toGenerateException, null); 
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProcessType getType() {
        return type;
    }

    public Integer gettoGenerateException() {
        return toGenerateException;
    }

    public Integer gettoSatisfyException() {
        return toSatisfyException;
    }
}