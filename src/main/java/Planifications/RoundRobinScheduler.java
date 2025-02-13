package Planifications;


/**
 *
 * @author beacardozo
 */
public class RoundRobinScheduler {
    private int numProcess; // Número total de procesos
    private int quantum; // Quantum de tiempo
    private int[] times; // Tiempo restante de ejecución de cada proceso
    private int[] wait; // Tiempo de espera de cada proceso

    public RoundRobinScheduler(int numProcess, int quantum, int[] B) {
        this.numProcess = numProcess;
        this.quantum = 5; //asignado asi
        this.times = times;
        this.wait = new int[numProcess]; // Se inicializa el array de tiempos de espera en 0
    }

    public void runRR() {
        int total = 0; // Tiempo total de ejecución
        int procesosTerminados = 0; // Contador de procesos terminados
        int tiempoActual = 0; // Representa el tiempo global de la simulación

        // Calcular el tiempo total inicial de todos los procesos
        for (int i = 0; i < numProcess; i++) {
            total += times[i];
        }

        while (procesosTerminados < numProcess) { // Mientras haya procesos sin terminar
            for (int i = 0; i < numProcess; i++) {
                if (times[i] > 0) { // Si el proceso aún tiene tiempo restante
                    int tiempoEjecutado = Math.min(times[i], quantum); // Ejecuta hasta el quantum o menos si queda poco tiempo
                    times[i] -= tiempoEjecutado;
                    tiempoActual += tiempoEjecutado;

                    // Actualizar tiempo de espera de los demás procesos
                    for (int j = 0; j < numProcess; j++) {
                        if (j != i && times[j] > 0) {
                            wait[j] += tiempoEjecutado;
                        }
                    }

                    // Si el proceso terminó, aumentar el contador de procesos terminados
                    if (times[i] == 0) {
                        procesosTerminados++;
                    }

                    System.out.println("Proceso " + (i + 1) + " ejecutado por " + tiempoEjecutado + " unidades de tiempo.");
                }
            }
        }

        // Calcular el tiempo promedio de espera
        int totalWait = 0;
        for (int i = 0; i < numProcess; i++) {
            totalWait += wait[i];
        }

        System.out.println("El tiempo promedio de espera es: " + (double) totalWait / numProcess);
    }
}

