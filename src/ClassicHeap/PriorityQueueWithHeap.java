package ClassicHeap;

import Other.MyPriorityQueue;

import java.util.ArrayList;

public class PriorityQueueWithHeap extends MyPriorityQueue {
    ArrayList<Integer> vertices;
    ArrayList<Integer> priorities;

    public PriorityQueueWithHeap(){
        this.vertices = new ArrayList<>();
        this.priorities = new ArrayList<>();
    }

    public void insert(int vertice, int priority){
        vertices.add(vertice);
        priorities.add(priority);
        verifyUp(vertices.size()-1);
    }

    public Integer extractMinimum(){
        int n = priorities.size() - 1;
        Integer minimum = vertices.get(0);

        vertices.set(0, vertices.get(n));
        priorities.set(0, priorities.get(n));

        vertices.remove(n);
        priorities.remove(n);

        int i = 0;
        while (2*i+1 < n){
            int k = 2*i + 1;
            if (k+1 < n && priorities.get(k+1) < priorities.get(k)){
                k++;
            }
            if (priorities.get(i) < priorities.get(k)){
                break;
            }
            Integer temporaryVertice = vertices.get(i);
            Integer temporaryPriority = priorities.get(i);

            vertices.set(i, vertices.get(k));
            priorities.set(i, priorities.get(k));

            vertices.set(k, temporaryVertice);
            priorities.set(k, temporaryPriority);

            i = k;
        }
        return minimum;
    }

    public void decreaseKey(int vertice, int newPriority){
        if (vertices.contains(vertice)){
            int index = vertices.indexOf(vertice);
            priorities.set(index, newPriority);
            verifyUp(index);
        }
    }

    private void verifyUp(int index){
        for (int i = index; i>0 && priorities.get(i) < priorities.get((i-1)/2); i = (i-1)/2){
            Integer temporaryVertice = vertices.get(i);
            Integer temporaryPriority = priorities.get(i);

            vertices.set(i, vertices.get((i-1)/2));
            priorities.set(i, priorities.get((i-1)/2));

            vertices.set((i-1)/2, temporaryVertice);
            priorities.set((i-1)/2, temporaryPriority);
        }
    }
}