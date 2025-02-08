/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ssoo;

/**
 *
 * @author Rodrigo
 */
import java.io.FileWriter;
import java.io.IOException;

public class Configuracion {
    private int duracionCiclo;
    private int numProcesadores;
    private String politicaPlanificacion;

    public void guardarConfiguracion(String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write("duracionCiclo,numProcesadores,politicaPlanificacion\n");
            writer.write(duracionCiclo + "," + numProcesadores + "," + politicaPlanificacion + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}