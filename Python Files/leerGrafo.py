import networkx as nx
import sys
import os

def leer_grafo_desde_archivo(archivo):
    grafo = nx.DiGraph()
    with open(archivo, 'r') as f:
        for linea in f:
            partes = linea.strip().split()
            ciudad1 = partes[0]
            ciudad2 = partes[1]
            distancia = int(partes[2])
            grafo.add_edge(ciudad1, ciudad2, weight=distancia)
    return grafo

def floyd_warshall(grafo):
    predecesores, distancias = nx.floyd_warshall_predecessor_and_distance(grafo)
    return predecesores, distancias

def encontrar_centro(grafo, distancias):
    excentricidades = {}
    for ciudad in grafo.nodes:
        excentricidades[ciudad] = max(distancias[ciudad].values())
    centro = min(excentricidades, key=excentricidades.get)
    return centro

def ruta_mas_corta(predecesores, origen, destino):
    if origen == destino:
        return [origen]
    if destino not in predecesores[origen]:
        return None
    ruta = []
    while destino is not None:
        ruta.append(destino)
        destino = predecesores[origen].get(destino)
    ruta.reverse()
    return ruta

# Main
archivo = 'guategrafo.txt'

def mostrar_opciones():
    print("Seleccione una opción:")
    print("1. Encontrar la ruta más corta entre dos ciudades.")
    print("2. Encontrar la ciudad centro del grafo.")
    print("3. Modificar el grafo.")
    print("4. Finalizar programa.")

def main():
    if not os.path.isfile(archivo):
        print(f"El archivo {archivo} no existe.")
        sys.exit(1)
    
    grafo = leer_grafo_desde_archivo(archivo)
    predecesores, distancias = floyd_warshall(grafo)
    
    while True:
        mostrar_opciones()
        opcion = input().strip()

        if opcion == '1':
            origen = input("Ingrese la ciudad origen: ").strip()
            destino = input("Ingrese la ciudad destino: ").strip()
            if origen in grafo.nodes and destino in grafo.nodes:
                ruta = ruta_mas_corta(predecesores, origen, destino)
                if ruta:
                    print(f"Distancia más corta: {distancias[origen][destino]}")
                    print(f"Ruta: {' -> '.join(ruta)}")
                else:
                    print(f"No existe una ruta entre {origen} y {destino}")
            else:
                print("Una o ambas ciudades no existen en el grafo.")
        elif opcion == '2':
            centro = encontrar_centro(grafo, distancias)
            print(f"La ciudad centro del grafo es: {centro}")
        elif opcion == '3':
            print("Seleccione una opción:")
            print("a. Interrupción de tráfico entre un par de ciudades.")
            print("b. Establecer una nueva conexión entre dos ciudades.")
            subopcion = input().strip()
            origen = input("Ingrese la ciudad origen: ").strip()
            destino = input("Ingrese la ciudad destino: ").strip()
            if subopcion == 'a':
                if grafo.has_edge(origen, destino):
                    grafo.remove_edge(origen, destino)
                    predecesores, distancias = floyd_warshall(grafo)
                else:
                    print("No existe una conexión directa entre esas ciudades.")
            elif subopcion == 'b':
                distancia = int(input("Ingrese la distancia en KM: ").strip())
                grafo.add_edge(origen, destino, weight=distancia)
                predecesores, distancias = floyd_warshall(grafo)
            else:
                print("Opción inválida.")
        elif opcion == '4':
            break
        else:
            print("Opción inválida. Intente de nuevo.")

if __name__ == "__main__":
    main()
