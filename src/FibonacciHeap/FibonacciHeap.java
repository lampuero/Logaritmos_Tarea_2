package FibonacciHeap;

import Other.MyPriorityQueue;

public class FibonacciHeap extends MyPriorityQueue {
    Nodo min;
    int n;

    public FibonacciHeap(){
        min = null;
        n = 0;
    }

    private void consolidate(){}

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
        return false;
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