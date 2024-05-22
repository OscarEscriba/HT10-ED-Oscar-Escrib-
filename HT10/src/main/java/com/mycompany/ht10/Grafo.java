/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ht10;

/**
 *
 * @author Oscar Escriba
 */
import java.util.*;

public class Grafo {
    private final Map<String, Integer> indices;
    private final List<String> ciudades;
    private final int[][] matrizAdyacencia;
    private final int INF = Integer.MAX_VALUE / 2; // Uso de un valor grande para representar la ausencia de conexión.

    public Grafo(int numCiudades) {
        indices = new HashMap<>();
        ciudades = new ArrayList<>();
        matrizAdyacencia = new int[numCiudades][numCiudades];
        for (int i = 0; i < numCiudades; i++) {
            Arrays.fill(matrizAdyacencia[i], INF);
            matrizAdyacencia[i][i] = 0; // La distancia desde una ciudad a sí misma es 0.
        }
    }

    public void agregarCiudad(String ciudad) {
        if (!indices.containsKey(ciudad)) {
            indices.put(ciudad, ciudades.size());
            ciudades.add(ciudad);
        }
    }

    public void agregarArco(String origen, String destino, int distancia) {
        int i = indices.get(origen);
        int j = indices.get(destino);
        matrizAdyacencia[i][j] = distancia;
    }

    public void eliminarArco(String origen, String destino) {
        int i = indices.get(origen);
        int j = indices.get(destino);
        matrizAdyacencia[i][j] = INF;
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public List<String> getCiudades() {
        return ciudades;
    }

    public int getIndiceCiudad(String ciudad) {
        return indices.get(ciudad);
    }
}
