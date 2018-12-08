package ClassicHeap;

import java.util.ArrayList;

public class PriorityQueueWithHeap {
    ArrayList<Element> elements;

    public PriorityQueueWithHeap(){
        this.elements = new ArrayList<>();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void insert(int vertice, int priority){
        elements.add(new Element(vertice, priority));
        verifyUp(elements.size()-1);
    }

    public int extractMinimum(){
        int n = elements.size() - 1;
        Element minimum = elements.get(0);

        elements.set(0, elements.get(n));

        elements.remove(n);

        int i = 0;
        while (2*i+1 < n){
            int k = 2*i + 1;
            if (k+1 < n && elements.get(k+1).priority < elements.get(k).priority){
                k++;
            }
            if (elements.get(i).priority < elements.get(k).priority){
                break;
            }
            Element tmp = elements.get(i);

            elements.set(i, elements.get(k));

            elements.set(k, tmp);

            i = k;
        }
        return minimum.vertex;
    }

    public void decreaseKey(int vertice, int newPriority){
        int index = elements.indexOf(new Element(vertice, 0));
        Element tmp = elements.get(index);
        tmp.priority = newPriority;
        verifyUp(index);
    }

    private void verifyUp(int index){
        for (int i = index; i>0 && elements.get(i).priority < elements.get((i-1)/2).priority; i = (i-1)/2){
            Element tmp = elements.get(i);

            elements.set(i, elements.get((i-1)/2));

            elements.set((i-1)/2, tmp);
        }
    }
}

class Element{
    int vertex;
    int priority;

    public Element(int vertex, int priority){
        this.vertex = vertex;
        this.priority = priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Element)) {
            return false;
        }
        Element otherElement = (Element) obj;
        return vertex == otherElement.vertex;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + vertex;
        return result;
    }
}
