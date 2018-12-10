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
        long startProgram = System.currentTimeMillis();
        Random random = new Random();
        int n = 100000;
        int[] muls = {10, 100};
        for (int mul: muls){
            System.out.printf("Empiezo con %dn\n", mul);
            int e = mul*n;
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
            System.out.println("Empezando experimentos.");
            int experimentos = 20;
            int calentamiento = 3;
            float[] tiempoNaive = new float[experimentos];
            float[] tiempoClassicHeap = new float[experimentos];
            float[] tiempoFibonacciHeap = new float[experimentos];
            System.out.println("Empezando calentamiento para implementacion Naive.");
            for (int i = 0; i < calentamiento ; i++) {
                ResultDijkstra res = Naive.algorithmDijkstra(origin, grafo);
            }
            System.out.println("Termino calentamiento.");
            System.out.println("Empezando experimentos con implementacion Naive.");
            for (int i = 0; i < experimentos ; i++) {
                long startTime = System.currentTimeMillis();
                ResultDijkstra res1 = Naive.algorithmDijkstra(origin, grafo);
                long endTime = System.currentTimeMillis();
                tiempoNaive[i] = (endTime - startTime)/1000f;
            }

            System.out.println("Empezando calentamiento para Classic Heap.");
            for (int i = 0; i < calentamiento ; i++) {
                ResultDijkstra res = ClassicHeap.algorithmDijkstra(origin, grafo);
            }
            System.out.println("Termino calentamiento.");
            System.out.println("Empezando experimentos con Classic Heap.");
            for (int i = 0; i < experimentos ; i++) {
                long startTime = System.currentTimeMillis();
                ResultDijkstra res1 = ClassicHeap.algorithmDijkstra(origin, grafo);
                long endTime = System.currentTimeMillis();
                tiempoClassicHeap[i] = (endTime - startTime)/1000f;
            }

            System.out.println("Empezando calentamiento para Fibonacci Heap.");
            for (int i = 0; i < calentamiento ; i++) {
                ResultDijkstra res = FibonacciHeap.algorithmDijkstra(origin, grafo);
            }
            System.out.println("Termino calentamiento.");
            System.out.println("Empezando experimentos con Fibonacci Heap.");
            for (int i = 0; i < experimentos ; i++) {
                long startTime = System.currentTimeMillis();
                ResultDijkstra res1 = FibonacciHeap.algorithmDijkstra(origin, grafo);
                long endTime = System.currentTimeMillis();
                tiempoFibonacciHeap[i] = (endTime - startTime)/1000f;
            }
            System.out.println("Terminaron los experimentos.");
            System.out.println("Empiezo a escribir los resultados.");
            BufferedWriter naive = null;
            BufferedWriter classicHeap = null;
            BufferedWriter fibonacciHeap = null;
            BufferedWriter all = null;
            BufferedWriter prom = null;
            try {
                naive = new BufferedWriter(new FileWriter(String.format("Resultados Naive con %dn aristas.csv", mul)));
                classicHeap = new BufferedWriter(new FileWriter(String.format("Resultados Heap con %dn aristas.csv", mul)));
                fibonacciHeap = new BufferedWriter(new FileWriter(String.format("Resultados fibonacci Heap con %dn aristas.csv", mul)));
                all = new BufferedWriter(new FileWriter(String.format("Todos los resultados con %dn aristas.csv", mul)));
                prom = new BufferedWriter(new FileWriter(String.format("Resultados promedio con %dn aristas.csv", mul)));

                naive.write("Nº Experimento;Naive\n");
                classicHeap.write("Nº Experimento;ClassicHeap\n");
                fibonacciHeap.write("Nº Experimento;FibonacciHeap\n");
                all.write("Nº Experimento;Naive;ClassicHeap;FibonacciHeap\n");
                prom.write("Nº Experimento;Naive;ClassicHeap;FibonacciHeap\n");
                float[] average = new float[3];
                float[] total = {0f,0f,0f};
                for (int i = 0; i < experimentos; i++) {
                    naive.write(String.format("%d;%.3f\n", i+1, tiempoNaive[i]));
                    classicHeap.write(String.format("%d;%.3f\n", i+1, tiempoClassicHeap[i]));
                    fibonacciHeap.write(String.format("%d;%.3f\n", i+1, tiempoFibonacciHeap[i]));
                    all.write(String.format("%d;%.3f;%.3f;%.3f\n", i+1, tiempoNaive[i], tiempoClassicHeap[i], tiempoFibonacciHeap[i]));
                    total[0] += tiempoNaive[i];
                    total[1] += tiempoClassicHeap[i];
                    total[2] += tiempoFibonacciHeap[i];
                }
                for (int i = 0; i < 3; i++) {
                    average[i] = total[i]/experimentos;
                }
                prom.write(String.format("Promedio;%.3f;%.3f;%.3f\n", average[0], average[1], average[2]));

                System.out.printf("Termino de escribir los resultados para %dn\n.", mul);
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
        System.out.printf("duracion del programa fue %.3f s", (System.currentTimeMillis()-startProgram)/1000f);
    }
}
