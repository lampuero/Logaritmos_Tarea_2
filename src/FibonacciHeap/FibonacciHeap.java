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
            for (Pair pair: grafo[m]){
                int newpriority = dist[m] + pair.getDistance();
                if (newpriority < dist[pair.getVertice()]){
                    dist[pair.getVertice()] = newpriority;
                    prev[pair.getVertice()] = m;
                    queue.decreaseKey(nodos[pair.getVertice()], newpriority);
                }
            }
        }
        return new ResultDijkstra(dist, prev);
    }
}
