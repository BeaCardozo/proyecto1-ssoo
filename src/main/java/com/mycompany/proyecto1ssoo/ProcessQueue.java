package com.mycompany.proyecto1ssoo;


import java.io.BufferedWriter;
import java.io.IOException;
import javax.swing.DefaultListModel;

/**
 *
 * @author Rodrigo
 */
public class ProcessQueue {
    private Process[] processes;
    private int front;
    private int end;
    private int capacity;

    // Constructor
    public ProcessQueue(int capacity) {
        this.capacity = capacity;
        this.processes = new Process[capacity];
        this.front = 0;
        this.end = -1;
    }

    public void add(Process process) {
        if (end < capacity - 1) {
            end++;
            processes[end] = process;
        } else {
            //System.out.println("Queue full, cannot add more processes.");
        }
    }

    public Process remove() {
        if (front > end) {
            System.out.println("Queue empty, no processes to remove.");
            return null;
        } else {
            Process process = processes[front];
            front++;
            return process;
        }
    }
    
    // Obtener el índice del frente de la cola
    public int getFront() {
        return front;
    }

    // Obtener el índice del final de la cola
    public int getEnd() {
        return end;
    }
    
      public int decreaseEnd() {
        return end--;
    }

    
    
    public boolean remove(Process processToRemove) {
    for (int i = front; i <= end; i++) {
        if (processes[i].equals(processToRemove)) {
            // Mover los elementos restantes una posición hacia la izquierda
            for (int j = i; j < end; j++) {
                processes[j] = processes[j + 1];
            }
            processes[end] = null; // Eliminar la referencia al último elemento
            end--; // Reducir el tamaño de la cola
            return true; // Proceso eliminado
        }
    }
    return false; // Proceso no encontrado
}
    
    public boolean containsName(String name) {
        for (int i = front; i <= end; i++) {
            if (processes[i].getName().equals(name)) { 
                return true; 
            }
        }
        return false; 
    }

    public boolean isEmpty() {
        return front > end;
    }

    public int size() {
        return end - front + 1;
    }
    
    public void clear() {
    front = 0;
    end = -1;
    //System.out.println("Queue cleared."); // Eliminar o mover este mensaje
}

    public Process get(int index) {
        if (index >= 0 && index < size()) {
            return processes[front + index]; 
        } else {
            return null; 
        }
    }
    
    public boolean contains(Process processToFind) {
    for (int i = front; i <= end; i++) {
        if (processes[i].equals(processToFind)) {
            return true; // El proceso está en la cola
        }
    }
    return false; // El proceso no está en la cola
}
    
   public DefaultListModel<String> getProcessNames() {
    DefaultListModel<String> processNamesModel = new DefaultListModel<>();
    for (int i = 0; i < processes.length; i++) {
        Process process = processes[i];
        if (process != null) {
            processNamesModel.addElement(process.getName());
        }
    }
    return processNamesModel;
    }
   
   
   public Process[] getProcesses() {
        Process[] currentProcesses = new Process[end + 1];
        System.arraycopy(processes, 0, currentProcesses, 0, end + 1);
        return currentProcesses; // Devuelve solo los procesos en uso
    }
    


    public void iterateProcesses(BufferedWriter writer) throws IOException {
    if (isEmpty()) {
        writer.write("No hay procesos en la cola.");
        writer.newLine();
        return;
    }
    int i = front;
    while (true) {
        Process process = processes[i];
        if (process != null) {
            writer.write(process.getId() + ", " +              
                         process.getName() + ", " +            
                         process.getInstructions() + ", " +    
                         (process.isCpuBound() ? "true" : "false") + ", " + 
                         process.getExceptionCycles() + ", " +  
                         process.getSatisfactionCycles() + ", " + 
                         process.getProgramCounter() + ", " +   
                         process.getMAR() + ", " + 
                         process.getState());             
            writer.newLine();
        }
        if (i == end) break; 
        i = (i + 1) % capacity; 
    }
    }
    
    
    public void printQueue() {
        if (end == -1) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue contents: ");
        for (int i = front; i <= end; i++) {
            System.out.print(processes[i].getName() + ", ");
        }
        System.out.println();
        }
    }
