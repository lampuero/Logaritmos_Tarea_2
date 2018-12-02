import Other.Pair;

import java.util.ArrayList;

public class Experiments {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hola");
        System.out.println(3/2);
        ArrayList<Integer> a = new ArrayList<>();
        a.add(10);
        a.add(11);
        a.add(12);
        System.out.println(a);
        System.out.println(a.get(1));
        System.out.println(a);
        System.out.println(a.size());
        System.out.println(a.contains(11));
        System.out.println(a.indexOf(12));
        ArrayList<Pair> p = new ArrayList<>();
        p.add(new Pair(7,4));
        System.out.println(p);
        System.out.println(p.contains(new Pair(7,37)));

    }
}
