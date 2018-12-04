import Other.Pair;

import java.util.ArrayList;
import java.util.Random;

public class Experiments {

    public static void main(String[] args) {
        Random random = new Random();
        int n = 10;
        int e;
        ArrayList<Pair>[] grafo = new ArrayList[n];
        for (ArrayList array: grafo) {
            array = new ArrayList();
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
        long startTime = System.currentTimeMillis();
    }
}
