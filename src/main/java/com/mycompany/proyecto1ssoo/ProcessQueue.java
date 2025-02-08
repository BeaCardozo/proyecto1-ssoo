package com.mycompany.proyecto1ssoo;

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
            System.out.println("Queue full, cannot add more processes.");
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

    public boolean isEmpty() {
        return front > end;
    }

    public int size() {
        return end - front + 1;
    }

    public Process get(int index) {
        if (index >= 0 && index < size()) {
            return processes[front + index];
        } else {
            System.out.println("Index out of range.");
            return null;
        }
    }
}

