package Other;

import java.util.ArrayList;

public class Grafo {
    Arista[] grafo;
    int numeroVertices;
    ArrayList<String>[] group = new ArrayList[4];

    public Grafo(int n){
        this.numeroVertices = n;
        grafo = new Arista[n];
    }




}

class Arista {
    int vertice;
    int distanciaArista;
    Arista sgte;

    public Arista(int vertice, int distancia){
        this.vertice = vertice;
        this.distanciaArista = distancia;
        this.sgte = null;
    }
}