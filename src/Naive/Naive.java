package Naive;

import Experimentos.Pair;
import Experimentos.ResultadoDijkstra;

import java.util.ArrayList;
import java.util.Iterator;

public class Naive {
    int[] dist;
    boolean[] marcado;
    Integer[] prev;

    public void inicializar(int n, int origen){
        dist = new int[n];
        marcado = new boolean[n];
        prev = new Integer[n];
        for (int i = 0; i < n ; i++){
            marcado[i] = false;
            prev[i] = null;
            if (i == origen) {
                dist[origen] = 0;
            } else {
                dist[i] = Integer.MAX_VALUE;
            }
        }
    }

    public ResultadoDijkstra algoritmoDijkstra(int origen, ArrayList<Pair>[] grafo){
        int n = grafo.length;
        inicializar(n, origen);
        for (int i = 0; i < n-1; i++){
            int minDist = Integer.MAX_VALUE;
            int minNodo = -1;

            //buscar el mınimo
            for (int j = 0; j< n; j++){
                if (!marcado[j] && (dist[j] < minDist)){
                    minDist = dist[j];
                    minNodo = j;
                }
            }
            int u = minNodo;
            marcado[u] = true;

            //actualizar distancias
            for (Pair pair: grafo[u]) {
                if (dist[pair.vertice] > dist[u]+pair.distancia){
                    dist[pair.vertice] = dist[u]+pair.distancia;
                    prev[pair.vertice] = u;
                }
            }
        }
        return new ResultadoDijkstra(dist, prev);
    }
}
