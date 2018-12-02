package Other;

import java.util.ArrayList;

public abstract class DijkstraWithPriorityQueue {
    MyPriorityQueue queue;
    int[] dist;
    Integer[] prev;


    public ResultDijkstra algorithmDijkstra(int origen, ArrayList<Pair>[] grafo){
        int n = grafo.length;
        for (int i = 0; i < n; i++){
            if (i == origen){
                dist[i] = 0;
            }
            else {
                dist[i] = Integer.MAX_VALUE;
            }
            prev[i] = null;
            queue.insert(i, dist[i]);
        }

        while (!queue.isEmpty()){
            int m = queue.extractMinimum();
            for (Pair pair: grafo[m]){
                int newpriority = dist[m] + pair.getDistance();
                if (newpriority < dist[pair.getVertice()]){
                    dist[pair.getVertice()] = newpriority;
                    prev[pair.getVertice()] = m;
                    queue.decreaseKey(pair.getVertice(), newpriority);
                }
            }
        }
        return new ResultDijkstra(dist, prev);
    }
}
