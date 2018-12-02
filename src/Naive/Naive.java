package Naive;

import Other.Pair;
import Other.ResultDijkstra;

import java.util.ArrayList;

public class Naive {
    private static int[] dist;
    private static boolean[] marcado;
    private static Integer[] prev;

    private static void initialize(int n, int origen){
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

    public static ResultDijkstra algorithmDijkstra(int origen, ArrayList<Pair>[] grafo){
        int n = grafo.length;
        initialize(n, origen);
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
                    prev[pair.getDistance()] = u;
                }
            }
        }
        return new ResultDijkstra(dist, prev);
    }
}
