/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ht10;

/**
 *
 * @author Oscar Escriba
 */
import java.io.*;
import java.util.*;

public class HT10 {
    public static void main(String[] args) {
        Grafo grafo = leerGrafoDesdeArchivo("guategrafo.txt");

        FloydWarshall.floydWarshall(grafo);

        // Interactuar con el usuario
        Scanner scanner = new Scanner(System.in);
        boolean ejecutar = true;
        while (ejecutar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Encontrar la ruta más corta entre dos ciudades.");
            System.out.println("2. Encontrar la ciudad centro del grafo.");
            System.out.println("3. Modificar el grafo.");
            System.out.println("4. Finalizar programa.");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea.

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la ciudad origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ingrese la ciudad destino: ");
                    String destino = scanner.nextLine();
                    System.out.println("Distancia más corta: " + FloydWarshall.distanciaMasCorta(grafo, origen, destino));
                    System.out.println("Ruta: " + FloydWarshall.rutaMasCorta(grafo, origen, destino));
                    break;
                case 2:
                    System.out.println("La ciudad centro del grafo es: " + FloydWarshall.encontrarCentro(grafo));
                    break;
                case 3:
                    System.out.println("Seleccione una opción:");
                    System.out.println("a. Interrupción de tráfico entre un par de ciudades.");
                    System.out.println("b. Establecer una nueva conexión entre dos ciudades.");
                    char opcionModificacion = scanner.nextLine().charAt(0);
                    System.out.print("Ingrese la ciudad origen: ");
                    origen = scanner.nextLine();
                    System.out.print("Ingrese la ciudad destino: ");
                    destino = scanner.nextLine();
                    if (opcionModificacion == 'a') {
                        grafo.eliminarArco(origen, destino);
                    } else if (opcionModificacion == 'b') {
                        System.out.print("Ingrese la distancia en KM: ");
                        int distancia = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea.
                        grafo.agregarArco(origen, destino, distancia);
                    }
                    FloydWarshall.floydWarshall(grafo); // Recalcular rutas más cortas.
                    break;
                case 4:
                    ejecutar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static Grafo leerGrafoDesdeArchivo(String archivo) {
        Grafo grafo = new Grafo(100); // Tamaño inicial arbitrario, puede ajustarse según el archivo.
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                String ciudad1 = partes[0];
                String ciudad2 = partes[1];
                int distancia = Integer.parseInt(partes[2]);
                grafo.agregarCiudad(ciudad1);
                grafo.agregarCiudad(ciudad2);
                grafo.agregarArco(ciudad1, ciudad2, distancia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grafo;
    }
}

