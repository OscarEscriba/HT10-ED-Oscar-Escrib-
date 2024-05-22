import unittest
import networkx as nx
from leerGrafo import leer_grafo_desde_archivo, floyd_warshall, encontrar_centro, ruta_mas_corta

class test(unittest.TestCase):

    def setUp(self):
        self.grafo = nx.DiGraph()
        self.grafo.add_edge('Mixco', 'Antigua', weight=30)
        self.grafo.add_edge('Antigua', 'Escuintla', weight=25)
        self.grafo.add_edge('Escuintla', 'SantaLucia', weight=15)
        self.grafo.add_edge('SantaLucia', 'Mazatenango', weight=20)
        # Añadir todas las demás conexiones de tu ejemplo aquí

        self.predecesores, self.distancias = floyd_warshall(self.grafo)

    def test_leer_grafo_desde_archivo(self):
        grafo_leido = leer_grafo_desde_archivo('guategrafo.txt')
        self.assertEqual(len(grafo_leido.nodes), len(self.grafo.nodes))
        self.assertEqual(len(grafo_leido.edges), len(self.grafo.edges))

    def test_floyd_warshall(self):
        self.assertIsNotNone(self.predecesores)
        self.assertIsNotNone(self.distancias)
        self.assertTrue('Antigua' in self.distancias['Mixco'])

    def test_ruta_mas_corta(self):
        ruta = ruta_mas_corta(self.predecesores, 'Mixco', 'Escuintla')
        self.assertEqual(ruta, ['Mixco', 'Antigua', 'Escuintla'])

    def test_encontrar_centro(self):
        centro = encontrar_centro(self.grafo, self.distancias)
        self.assertEqual(centro, 'Escuintla')

if __name__ == '__main__':
    unittest.main()
