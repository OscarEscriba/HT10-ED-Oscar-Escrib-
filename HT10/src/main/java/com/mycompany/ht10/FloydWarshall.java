/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ht10;

/**
 *
 * @author Oscar Escriba
 */

public class FloydWarshall {
    public static int[][] distancias;
    public static String[][] siguientes;

    public static void floydWarshall(Grafo grafo) {
        int n = grafo.getCiudades().size();
        distancias = new int[n][n];
        siguientes = new String[n][n];

        // Inicializar distancias y siguientes matrices.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distancias[i][j] = grafo.getMatrizAdyacencia()[i][j];
                if (grafo.getMatrizAdyacencia()[i][j] != Integer.MAX_VALUE / 2) {
                    siguientes[i][j] = grafo.getCiudades().get(j);
                }
            }
        }

        // Aplicar el algoritmo de Floyd-Warshall.
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][j] > distancias[i][k] + distancias[k][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        siguientes[i][j] = siguientes[i][k];
                    }
                }
            }
        }
    }

    public static String rutaMasCorta(Grafo grafo, String origen, String destino) {
        int i = grafo.getIndiceCiudad(origen);
        int j = grafo.getIndiceCiudad(destino);
        if (distancias[i][j] == Integer.MAX_VALUE / 2) {
            return "No existe una ruta entre " + origen + " y " + destino;
        }
        StringBuilder ruta = new StringBuilder(origen);
        while (!origen.equals(destino)) {
            origen = siguientes[grafo.getIndiceCiudad(origen)][grafo.getIndiceCiudad(destino)];
            ruta.append(" -> ").append(origen);
        }
        return ruta.toString();
    }

    public static int distanciaMasCorta(Grafo grafo, String origen, String destino) {
        int i = grafo.getIndiceCiudad(origen);
        int j = grafo.getIndiceCiudad(destino);
        return distancias[i][j];
    }

    public static String encontrarCentro(Grafo grafo) {
        int n = grafo.getCiudades().size();
        int[] maximasDistancias = new int[n];
        for (int i = 0; i < n; i++) {
            int maxDist = 0;
            for (int j = 0; j < n; j++) {
                if (distancias[i][j] != Integer.MAX_VALUE / 2) {
                    maxDist = Math.max(maxDist, distancias[i][j]);
                }
            }
            maximasDistancias[i] = maxDist;
        }
        int centro = 0;
        for (int i = 1; i < n; i++) {
            if (maximasDistancias[i] < maximasDistancias[centro]) {
                centro = i;
            }
        }
        return grafo.getCiudades().get(centro);
    }
}

