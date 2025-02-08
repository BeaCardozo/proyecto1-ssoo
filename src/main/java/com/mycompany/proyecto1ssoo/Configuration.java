package com.mycompany.proyecto1ssoo;

/**
 *
 * @author Rodrigo
 */
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    private int cycleDuration;
    private int numProcessors;
    private String schedulingPolicy;

    public void saveConfiguration(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("cycleDuration,numProcessors,schedulingPolicy\n");
            writer.write(cycleDuration + "," + numProcessors + "," + schedulingPolicy + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}