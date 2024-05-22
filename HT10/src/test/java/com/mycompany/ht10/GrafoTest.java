/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.ht10;
import org.junit.Test;
import static org.junit.Assert.*;

public class GrafoTest {

    @Test
    public void testAgregarCiudad() {
        Grafo grafo = new Grafo(100);
        grafo.agregarCiudad("Ciudad1");
        grafo.agregarCiudad("Ciudad2");

        assertTrue(grafo.getCiudades().contains("Ciudad1"));
        assertTrue(grafo.getCiudades().contains("Ciudad2"));
    }

    @Test
    public void testAgregarArco() {
        Grafo grafo = new Grafo(100);
        grafo.agregarCiudad("Ciudad1");
        grafo.agregarCiudad("Ciudad2");

        grafo.agregarArco("Ciudad1", "Ciudad2", 10);

        assertEquals(10, grafo.getMatrizAdyacencia()[0][1]);
    }

    @Test
    public void testEliminarArco() {
        Grafo grafo = new Grafo(100);
        grafo.agregarCiudad("Ciudad1");
        grafo.agregarCiudad("Ciudad2");
        grafo.agregarArco("Ciudad1", "Ciudad2", 10);

        grafo.eliminarArco("Ciudad1", "Ciudad2");

        assertEquals(Integer.MAX_VALUE / 2, grafo.getMatrizAdyacencia()[0][1]);
    }
}
