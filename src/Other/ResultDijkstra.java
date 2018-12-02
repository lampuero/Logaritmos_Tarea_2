package Other;

public class ResultDijkstra {
    public int[] distancias;
    public Integer[] camino;

    public ResultDijkstra(int[] dist, Integer[] prev){
        this.distancias = dist;
        this.camino = prev;
    }
}
