package com.mycompany.proyecto1ssoo;

/**
 *
 * @author beacardozo
 */
public enum SchedulingPolicy {
    
    FCFS("FCFS - First-Come, First-Served"),
    RR("RR - Round Robin"),
    SPN("SPN - Shortest Process Next"),
    HRRN("HRRN - Highest Response Ratio Next"),
    SRT("SRT - Shortest Remaining Time");

    private final String description;

    SchedulingPolicy(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static SchedulingPolicy fromString(String input) {
        for (SchedulingPolicy policy : SchedulingPolicy.values()) {
            if (policy.name().equalsIgnoreCase(input) || policy.getDescription().equalsIgnoreCase(input)) {
                return policy;
            }
        }
        return null; 
    }

}
