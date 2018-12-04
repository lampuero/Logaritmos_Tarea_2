import FibonacciHeap.FibonacciHeap;
import Naive.Naive;
import Other.Pair;
import Other.ResultDijkstra;

import java.util.ArrayList;
import java.util.Random;

public class Experiments {

    public static void main(String[] args) {
        Random random = new Random();
        int n = 100000;
        int e;
        ArrayList<Pair>[] grafo = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            grafo[i] = new ArrayList();
        }
        for (int i = 0; i < n-1; i++){
            int d = random.nextInt() + 1;
            grafo[i].add(new Pair(i+1,d));
            grafo[i+1].add(new Pair(i, d));
        }
        e = 10*n;
        int c = n-1;
        while (c < e){
            int i = random.nextInt(n);
            int j = random.nextInt(n);
            int d = random.nextInt() + 1;
            if (i != j && !grafo[i].contains(new Pair(j,d))){
                grafo[i].add(new Pair(j,d));
                grafo[j].add(new Pair(i,d));
                c++;
            }
        }
        int origin = random.nextInt(n);
        System.out.println("Empezando experimento.");
        long startTime = System.currentTimeMillis();
        ResultDijkstra res1 = Naive.algorithmDijkstra(origin, grafo);
        long endTime = System.currentTimeMillis();
        System.out.println("Termino experimento.");
        System.out.println(String.format("Naive se demoro %d ms", endTime-startTime));
        System.out.println(res1.distancias);
        System.out.println(res1.camino);
    }
}
