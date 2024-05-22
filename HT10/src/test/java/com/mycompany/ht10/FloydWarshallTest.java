/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.ht10;


/**
 *
 * @author Oscar Escriba
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class FloydWarshallTest {

    @Test
    public void testDistanciaMasCorta() {
        Grafo grafo = new Grafo(100);
        grafo.agregarCiudad("A");
        grafo.agregarCiudad("B");
        grafo.agregarCiudad("C");
        grafo.agregarArco("A", "B", 10);
        grafo.agregarArco("B", "C", 15);

        FloydWarshall.floydWarshall(grafo);

        assertEquals(10, FloydWarshall.distanciaMasCorta(grafo, "A", "B"));
        assertEquals(25, FloydWarshall.distanciaMasCorta(grafo, "A", "C"));
    }

    @Test
    public void testRutaMasCorta() {
        Grafo grafo = new Grafo(100);
        grafo.agregarCiudad("A");
        grafo.agregarCiudad("B");
        grafo.agregarCiudad("C");
        grafo.agregarArco("A", "B", 10);
        grafo.agregarArco("B", "C", 15);

        FloydWarshall.floydWarshall(grafo);

        assertEquals("A -> B", FloydWarshall.rutaMasCorta(grafo, "A", "B"));
        assertEquals("A -> B -> C", FloydWarshall.rutaMasCorta(grafo, "A", "C"));
    }

  @Test
    public void testEncontrarCentro() {
        Grafo grafo = new Grafo(100);
        grafo.agregarCiudad("A");
        grafo.agregarCiudad("B");
        grafo.agregarCiudad("C");
        grafo.agregarArco("A", "B", 10);
        grafo.agregarArco("B", "C", 15);
        grafo.agregarArco("C", "A", 20);  // Agregar arco adicional para ver el impacto

        FloydWarshall.floydWarshall(grafo);

        String centro = FloydWarshall.encontrarCentro(grafo);
        System.out.println("Centro del grafo: " + centro);

        // Dependiendo de la lógica esperada, ajusta la expectativa aquí
        assertEquals("B", centro);
    }
}
