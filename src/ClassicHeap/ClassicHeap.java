package ClassicHeap;

import Other.Pair;
import Other.ResultDijkstra;

import java.util.ArrayList;

public class ClassicHeap {

    public static ResultDijkstra algorithmDijkstra(int origen, ArrayList<Pair>[] grafo){
        int n = grafo.length;
        Element[] elements = new Element[n];
        int[] dist = new int[n];
        Integer[] prev = new Integer[n];
        PriorityQueueWithHeap queue = new PriorityQueueWithHeap();

        for (int i = 0; i < n; i++){
            if (i == origen){
                dist[i] = 0;
            }
            else {
                dist[i] = Integer.MAX_VALUE;
            }
            prev[i] = null;
            elements[i] = new Element(i, dist[i]);
            queue.insert(elements[i]);
        }

        while (!queue.isEmpty()){
            int m = queue.extractMinimum();
            for (Pair pair: grafo[m]){
                int newPriority = dist[m] + pair.distance;
                if (newPriority < dist[pair.vertice]){
                    dist[pair.vertice] = newPriority;
                    prev[pair.vertice] = m;
                    queue.decreaseKey(elements[pair.vertice], newPriority);
                }
            }
        }
        return new ResultDijkstra(dist, prev);
    }
}
