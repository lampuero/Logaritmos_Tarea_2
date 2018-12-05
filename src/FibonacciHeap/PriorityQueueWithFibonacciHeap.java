package FibonacciHeap;

public class PriorityQueueWithFibonacciHeap {
    Nodo min;
    int n;

    public PriorityQueueWithFibonacciHeap(){
        min = null;
        n = 0;
    }

    public void insert(Nodo nodo) {
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

    public int extractMinimum() {
        Nodo z = min;
        if (z != null){
            if (z.child != null){
                nullParent(z.child);
                jointMinList(z.child);
            }
            Nodo right = z.right;
            Nodo left = z.left;
            right.left = left;
            left.right = right;

            if (z == z.right){
                min = null;
            }
            else {
                min = z.right;
                consolidate();
            }
            n--;
            return z.vertice;
        }
        return -1;
    }

    private void consolidate(){
        Nodo[] nodos = new Nodo[n];
        for (int i = 0; i < n; i++) {
            nodos[i] = null;
        }

        Nodo tmp = min;
        Nodo stop = min.left;
        boolean coverAll = false;
        do {
            if (tmp == stop){
                coverAll = true;
            }
            Nodo x = tmp;
            tmp = tmp.right;
            int d = x.degree;

            Nodo right = x.right;
            Nodo left = x.left;
            right.left = left;
            left.right = right;
            x.right = x;
            x.left = x;

            while (nodos[d] != null){
                Nodo y = nodos[d];
                if (x.priority > y.priority){
                    Nodo a = x;
                    x = y;
                    y = a;
                }
                link(y, x);
                nodos[d] = null;
                d++;
            }
            nodos[d] = x;
        } while (!coverAll);
        min = null;
        for (int i = 0; i < n; i++) {
            if (nodos[i] != null){
                Nodo a = nodos[i];
                a.right = a;
                a.left = a;
                if (min == null){
                    min = a;
                }
                else {
                    jointMinList(a);
                    if (a.priority < min.priority){
                        min = a;
                    }
                }
            }
        }
    }

    private void link(Nodo y, Nodo x){
        Nodo right = y.right;
        Nodo left = y.left;
        right.left = left;
        left.right = right;

        if (x.child != null){
            right = x.child;
            left = right.left;

            y.right = right;
            y.left = left;

            right.left = y;
            left.right = y;
        }
        else {
            x.child = y;

        }
        y.parent = x;
        x.degree++;
        y.mark = false;
    }

    private void jointMinList(Nodo from){
        Nodo minLeft = min.left;
        Nodo fromRight = from.right;

        min.left = from;
        minLeft.right = fromRight;

        from.right = min;
        fromRight.left = minLeft;
    }

    private void nullParent(Nodo child){
        Nodo tmp = child;
        do {
            tmp.parent = null;
            tmp = tmp.right;
        } while (tmp != child);
    }



    public boolean isEmpty() {
        return min == null;
    }

    public void decreaseKey(Nodo x, int newPriority) {
        if (x.priority < newPriority){
            System.out.println("Error");
        }
        else {
            x.priority = newPriority;
            Nodo y = x.parent;
            if (y != null && x.priority < y.priority){
                cut(x, y);
                cascadingCut(y);
            }
            if (x.priority < min.priority){
                min = x;
            }
        }
    }

    private void cascadingCut(Nodo y){
        Nodo z = y.parent;
        if (z != null){
            if (!y.mark){
                y.mark = true;
            }
            else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    private void cut(Nodo x, Nodo y){
        if (y.child == x){
            if (y.degree > 1){
                y.child = x.right;
            }
            else {
                y.child = null;
            }
        }
        Nodo right = x.right;
        Nodo left = x.left;
        right.left = left;
        left.right = right;
        y.degree--;

        x.right = x;
        x.left = x;

        jointMinList(x);
        x.parent = null;
        x.mark = false;
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

    @Override
    public String toString() {
        return String.format("(%d,%d)", vertice, priority);
    }
}
