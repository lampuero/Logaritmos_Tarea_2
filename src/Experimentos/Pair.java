package Experimentos;

import java.io.Serializable;

public class Pair implements Serializable {
    public int vertice;
    public int distancia;

    public Pair(int v, int d){
        this.vertice = v;
        this.distancia = d;
    }
}
