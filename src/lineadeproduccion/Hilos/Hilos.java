/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lineadeproduccion.Hilos;

import javax.swing.SwingUtilities;
import lineadeproduccion.GUI.GUI;

/**
 *
 * @author HP
 */
public class Hilos extends Thread{
   private int cantidadArticulos;
    private GUI gui;

    public Hilos(int cantidadArticulos, GUI gui) {
        this.cantidadArticulos = cantidadArticulos;
        this.gui = gui;
    }

    @Override
    public void run() {
        int empaquetados = 0;
        int[] camiones = new int[5]; // Cada camión tiene un límite de 20 artículos

        for (int i = 1; i <= cantidadArticulos; i++) {
            try {
                Thread.sleep(500); // Simula el tiempo de empaquetado
                empaquetados++;

                // Actualizar empaquetados
                int finalEmpaquetados = empaquetados;
                SwingUtilities.invokeLater(() -> gui.actualizarContadorEmpaquetados(finalEmpaquetados));

                // Asignar a camiones
                int camion = (empaquetados - 1) / 20;
                if (camion < 5) {
                    camiones[camion]++;
                    int finalCamion = camion;
                    int finalCantidad = camiones[camion];
                    SwingUtilities.invokeLater(() -> gui.actualizarCamion(finalCamion, finalCantidad));
                }
            } catch (InterruptedException e) {
                System.err.println("Hilo interrumpido: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        // Mensaje final
        System.out.println("Procesamiento completo: " + empaquetados + " artículos empaquetados.");
    }
    
}
