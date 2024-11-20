/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lineadeproduccion.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lineadeproduccion.Hilos.Hilos;

/**
 *
 * @author HP
 */
public class GUI extends JFrame{
    private JLabel contadorArticulos;
    private JLabel contadorEmpaquetados;
    private JLabel[] camiones;

    public GUI() {
        setTitle("Línea de Producción");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1)); // Una fila por sección de información
        
        
        // Inicializar etiquetas
        contadorArticulos = new JLabel("Artículos ingresados: 0");
        contadorEmpaquetados = new JLabel("Artículos empaquetados: 0");

        // Inicializar camiones
        camiones = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            camiones[i] = new JLabel("Camión " + (i + 1) + ": 0 artículos");
            add(camiones[i]);
        }

        // Añadir etiquetas a la ventana
         // Añadir el fondo como último componente
        
        add(contadorArticulos);
        add(contadorEmpaquetados);
  
        setVisible(true);
        
    }
    public void mai() {
        GUI gui = new GUI();

        while (true) {
            String input = JOptionPane.showInputDialog("Dame la cantidad de los artículos y >S< para terminar:");
            if (input == null || input.equalsIgnoreCase("S")) {
                break;
            }

            try {
                int cantidadArticulos = Integer.parseInt(input);
                gui.actualizarContadorArticulos(cantidadArticulos);
                Hilos hilos = new Hilos(cantidadArticulos, gui);
                hilos.start();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
            }
        }
    }

    public void actualizarContadorArticulos(int cantidad) {
        contadorArticulos.setText("Artículos ingresados: " + cantidad);
    }

    public void actualizarContadorEmpaquetados(int cantidad) {
        contadorEmpaquetados.setText("Artículos empaquetados: " + cantidad);
    }

    public void actualizarCamion(int numeroCamion, int cantidad) {
        camiones[numeroCamion].setText("Camión " + (numeroCamion + 1) + ": " + cantidad + " artículos");
    }
}
