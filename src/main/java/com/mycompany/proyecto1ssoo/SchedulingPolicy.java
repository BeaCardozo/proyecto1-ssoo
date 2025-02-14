package com.mycompany.proyecto1ssoo;

/**
 *
 * @author beacardozo
 */
public enum SchedulingPolicy {
    FCFS("FCFS - First-Come, First-Served") {
        @Override
        public ProcessQueue reorder(Simulator simulator) {
            return simulator.reorderByFCFS();
        }
    },
    RR("RR - Round Robin") {
        @Override
        public ProcessQueue reorder(Simulator simulator) {
            return simulator.reorderByRoundRobin();
        }
    },
    SPN("SPN - Shortest Process Next") {
        @Override
        public ProcessQueue reorder(Simulator simulator) {
            return simulator.reorderBySPN();
        }
    },
    HRRN("HRRN - Highest Response Ratio Next") {
        @Override
        public ProcessQueue reorder(Simulator simulator) {
            return simulator.reorderByHRRN();
        }
    },
    SRT("SRT - Shortest Remaining Time") {
        @Override
        public ProcessQueue reorder(Simulator simulator) {
            return simulator.reorderBySRT();
        }
    };

    private final String description;


    SchedulingPolicy(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
    
    
    public static SchedulingPolicy fromString(String description) {
    for (SchedulingPolicy policy : SchedulingPolicy.values()) {
        if (policy.toString().equalsIgnoreCase(description)) {
            return policy;
        }
    }
    return null; 
}

    public abstract ProcessQueue reorder(Simulator simulator);
}
