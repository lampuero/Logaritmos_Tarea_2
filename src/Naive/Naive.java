package Naive;

import Other.Pair;
import Other.ResultDijkstra;

import java.util.ArrayList;

public class Naive {

    public static ResultDijkstra algorithmDijkstra(int origen, ArrayList<Pair>[] grafo){
        int n = grafo.length;
        int[] dist = new int[n];
        boolean[] marcado = new boolean[n];
        Integer[] prev = new Integer[n];
        for (int i = 0; i < n ; i++){
            marcado[i] = false;
            prev[i] = null;
            if (i == origen) {
                dist[origen] = 0;
            } else {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 1; i < n; i++){
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
                if (dist[pair.getVertice()] > dist[u]+pair.getDistance()){
                    dist[pair.getVertice()] = dist[u]+pair.getDistance();
                    prev[pair.getVertice()] = u;
                }
            }
        }
        return new ResultDijkstra(dist, prev);
    }
}
