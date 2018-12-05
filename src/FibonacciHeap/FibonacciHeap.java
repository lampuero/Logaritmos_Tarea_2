package FibonacciHeap;

import Other.Pair;
import Other.ResultDijkstra;

import java.util.ArrayList;

public class FibonacciHeap {


    public static ResultDijkstra algorithmDijkstra(int origen, ArrayList<Pair>[] grafo){
        int n = grafo.length;
        Nodo[] nodos = new Nodo[n];
        int[] dist = new int[n];
        Integer[] prev = new Integer[n];
        PriorityQueueWithFibonacciHeap queue = new PriorityQueueWithFibonacciHeap();

        for (int i = 0; i < n; i++){
            if (i == origen){
                dist[i] = 0;
            }
            else {
                dist[i] = Integer.MAX_VALUE;
            }
            prev[i] = null;
            nodos[i] = new Nodo(i, dist[i]);
            queue.insert(nodos[i]);
        }

        while (!queue.isEmpty()){
            int m = queue.extractMinimum();
            System.out.println(m);
            System.out.println(nodos[m]);
            for (Pair pair: grafo[m]){
                int newpriority = dist[m] + pair.distance;
                if (newpriority < dist[pair.vertice]){
                    dist[pair.vertice] = newpriority;
                    prev[pair.vertice] = m;
                    queue.decreaseKey(nodos[pair.vertice], newpriority);
                }
            }
        }
        return new ResultDijkstra(dist, prev);
    }
}
