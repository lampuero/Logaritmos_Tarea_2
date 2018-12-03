package FibonacciHeap;

import Other.MyPriorityQueue;

import java.util.ArrayList;

public class FibonacciHeap extends MyPriorityQueue {
    Nodo min;
    int n;

    public FibonacciHeap(){
        min = null;
        n = 0;
    }

    private void consolidate(){
        ArrayList<Nodo> array = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) {
            array.add(null);
        }
        Nodo tmp = min;
        do {
            Nodo x = tmp;
            int d = x.degree;
            while (d<=n && array.get(d) != null){
                Nodo y = array.get(d);
                if (x.priority < y.priority){
                    Nodo a = x;
                    x = y;
                    y = a;
                }
                link(y, x);
                array.set(d, null);
                d++;
            }
            array.set(d, x);
            tmp = tmp.right;
        } while (tmp != min);
        min = null;
        for (int i = 0; i <= n; i++) {
            if (array.get(i) != null){
                Nodo a = array.get(i);
                a.right = a;
                a.left = a;
                if (min == null){
                    min = a;
                }
                else {
                    jointList(min, a);
                    if (a.priority < min.priority){
                        min = a;
                    }
                }
            }
        }
    }

    private void link(Nodo y, Nodo x){
        removeFromList(y);
        y.left = y;
        y.right = y;

        if (x.child != null){
            jointList(x.child, y);
        }
        else {
            x.child = y;
        }
        x.degree++;
        y.mark = false;
    }

    @Override
    public int extractMinimum() {
        Nodo z = min;
        if (z != null){
            if (z.child != null){
                changeParent(z.child, null);
                jointList(z, z.child);

                removeFromList(z);

                if (z == z.right){
                    min = null;
                }
                else {
                    min = z.right;
                    consolidate();
                }
                n--;
            }
            return z.vertice;
        }
        return -1;
    }

    private void removeFromList(Nodo nodo){
        Nodo right = nodo.right;
        Nodo left = nodo.left;

        right.left = left;
        left.right = right;

        if (nodo.parent != null){
            nodo.parent.degree--;
        }
    }

    private void jointList(Nodo to, Nodo from){
        Nodo toLeft = to.left;
        Nodo fromRight = from.right;

        to.left = from;
        toLeft.right = fromRight;

        from.right = to;
        fromRight.left = toLeft;
    }

    private void changeParent(Nodo child, Nodo newParent){
        Nodo tmp = child;
        tmp.parent = newParent;
        while (tmp.right != child){
            tmp = tmp.right;
            tmp.parent = newParent;
        }
    }

    @Override
    public void insert(int vertice, int priority) {
        Nodo nodo = new Nodo(vertice, priority);

        if (min == null){
            min = nodo;
        }
        else {
            Nodo left = min.left;

            nodo.right = min;
            nodo.left = left;

            min.left = nodo;
            left.right = nodo;

            if (nodo.priority < min.priority){
                min = nodo;
            }
        }
        n++;
    }

    @Override
    public boolean isEmpty() {
        return min == null;
    }

    @Override
    public void decreaseKey(int vertice, int newPriority) {

    }
}

class Nodo{
    int degree;
    Nodo parent;
    Nodo left, right;
    Nodo child;
    boolean mark;

    int priority;
    int vertice;

    public Nodo(int vertice, int priority){
        this.vertice = vertice;
        this.priority = priority;

        degree = 0;
        parent = null;
        child = null;
        left = this;
        right = this;
        mark = false;
    }
}