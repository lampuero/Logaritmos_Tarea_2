package Experimentos;

public class ResultadoDijkstra {
    public int[] distancias;
    public Integer[] camino;

    public ResultadoDijkstra(int[] dist, Integer[] prev){
        this.distancias = dist;
        this.camino = prev;
    }
}
