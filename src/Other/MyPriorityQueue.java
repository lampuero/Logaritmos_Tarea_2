package Other;

public abstract class MyPriorityQueue {

    public abstract void insert(int vertice, int priority);

    public abstract void decreaseKey(int vertice, int newPriority);

    public abstract int extractMinimum();

    public abstract boolean isEmpty();
}
