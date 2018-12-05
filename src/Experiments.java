import ClassicHeap.ClassicHeap;
import FibonacciHeap.FibonacciHeap;
import Naive.Naive;
import Other.Pair;
import Other.ResultDijkstra;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Experiments {

    public static void main(String[] args) {
        Random random = new Random();
        int n = 100000;
        int[] muls = {10, 100, 1000};
        for (int m = 0; m < 3; m++){
            int e = muls[m]*n;
            ArrayList<Pair>[] grafo = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                grafo[i] = new ArrayList<>();
            }
            for (int i = 0; i < n-1; i++){
                int d = random.nextInt(100) + 1;
                grafo[i].add(new Pair(i+1,d));
                grafo[i+1].add(new Pair(i, d));
            }
            int c = n-1;
            while (c < e){
                int i = random.nextInt(n);
                int j = random.nextInt(n);
                int d = random.nextInt(100) + 1;
                if (i != j && !grafo[i].contains(new Pair(j,d))){
                    grafo[i].add(new Pair(j,d));
                    grafo[j].add(new Pair(i,d));
                    c++;
                }
            }
            int origin = random.nextInt(n);
            System.out.printf("Origen %d%n", origin);
            System.out.println("Empezando calentamiento.");
            for (int i = 0; i < 5 ; i++) {
                ResultDijkstra res1 = Naive.algorithmDijkstra(origin, grafo);
                ResultDijkstra res2 = ClassicHeap.algorithmDijkstra(origin, grafo);
                ResultDijkstra res3 = FibonacciHeap.algorithmDijkstra(origin, grafo);
                if (res1.equals(res2) && res2.equals(res3)){
                    System.out.println("Todo bien con los algoritmos.");
                }
                else {
                    System.out.println("Algo salio mal.");
                }
            }
            System.out.println("Termino calentamiento.");
            System.out.println("Empezando experimentos.");
            int experimentos = 20;
            float[] tiempoNaive = new float[experimentos];
            float[] tiempoClassicHeap = new float[experimentos];
            float[] tiempoFibonacciHeap = new float[experimentos];
            System.out.println("Empezando experimentos con implementacion Naive.");
            for (int i = 0; i < experimentos ; i++) {
                long startTime = System.currentTimeMillis();
                ResultDijkstra res1 = Naive.algorithmDijkstra(origin, grafo);
                long endTime = System.currentTimeMillis();
                tiempoNaive[i] = (endTime - startTime)/1000f;
            }
            System.out.println("Empezando experimentos con Classic Heap.");
            for (int i = 0; i < experimentos ; i++) {
                long startTime = System.currentTimeMillis();
                ResultDijkstra res1 = ClassicHeap.algorithmDijkstra(origin, grafo);
                long endTime = System.currentTimeMillis();
                tiempoClassicHeap[i] = (endTime - startTime)/1000f;
            }
            System.out.println("Empezando experimentos con Fibonacci Heap.");
            for (int i = 0; i < experimentos ; i++) {
                long startTime = System.currentTimeMillis();
                ResultDijkstra res1 = FibonacciHeap.algorithmDijkstra(origin, grafo);
                long endTime = System.currentTimeMillis();
                tiempoFibonacciHeap[i] = (endTime - startTime)/1000f;
            }
            System.out.println("Terminaron los experimentos.");
            BufferedWriter naive = null;
            BufferedWriter classicHeap = null;
            BufferedWriter fibonacciHeap = null;
            BufferedWriter all = null;
            BufferedWriter prom = null;
            try {
                naive = new BufferedWriter(new FileWriter(String.format("Resultados Naive con %d*n aristas.csv", muls[m])));
                classicHeap = new BufferedWriter(new FileWriter(String.format("Resultados Heap con %d*n aristas.csv", muls[m])));
                fibonacciHeap = new BufferedWriter(new FileWriter(String.format("Resultados fibonacci Heap con %d*n aristas.csv", muls[m])));
                all = new BufferedWriter(new FileWriter(String.format("Todos los resultados con %d*n aristas.csv", muls[m])));
                prom = new BufferedWriter(new FileWriter(String.format("Resultados promedio con %d*n aristas.csv", muls[m])));

                naive.write("Nº Experimento;Naive\n");
                classicHeap.write("Nº Experimento;ClassicHeap\n");
                fibonacciHeap.write("Nº Experimento;FibonacciHeap\n");
                all.write("Nº Experimento;Naive;ClassicHeap;FibonacciHeap\n");
                prom.write("Nº Experimento;Naive;ClassicHeap;FibonacciHeap\n");
                float[] average = new float[3];
                float[] total = new float[3];
                for (int i = 1; i <= experimentos; i++) {
                    naive.write("");
                }

            }
            catch (Exception exc){
                exc.printStackTrace();
            }
            finally {
                try {
                    if (naive != null){
                        naive.close();
                    }
                    if (classicHeap != null){
                        classicHeap.close();
                    }
                    if (fibonacciHeap != null){
                        fibonacciHeap.close();
                    }
                    if (all != null){
                        all.close();
                    }
                    if (prom != null){
                        prom.close();
                    }
                }
                catch (Exception exc){
                    exc.printStackTrace();
                }


            }

        }


    }
}
