package FibonacciHeap;

public class PriorityQueueWithFibonacciHeap {
    Nodo min;
    int n;

    public PriorityQueueWithFibonacciHeap(){
        min = null;
        n = 0;
    }

    private void consolidate(){
        Nodo[] nodos = new Nodo[n];
        for (int i = 0; i < n; i++) {
            nodos[i] = null;
        }
        Nodo tmp = min;
        do {
            Nodo x = tmp;
            int d = x.degree;
            while (d<=n && nodos[d] != null){
                Nodo y = nodos[d];
                if (x.priority < y.priority){
                    Nodo a = x;
                    x = y;
                    y = a;
                }
                link(y, x);
                nodos[d] = null;
                d++;
            }
            nodos[d] = x;
            tmp = tmp.right;
        } while (tmp != min);
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
            changeParent(y, x);
        }
        else {
            x.child = y;
        }
        x.degree++;
        y.mark = false;
    }

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
        do {
            tmp.parent = newParent;
            tmp = tmp.right;
        } while (tmp.right != child);
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
        removeFromList(x);
        y.degree--;

        x.right = x;
        x.left = x;

        jointList(min, x);
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
}
