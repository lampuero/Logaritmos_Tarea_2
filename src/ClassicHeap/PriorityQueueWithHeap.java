package ClassicHeap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PriorityQueueWithHeap {
    Element[] elements;
    Map<Integer,Integer> indexOf;
    int lastIndex;

    public PriorityQueueWithHeap(int lenghtArray){
        this.elements = new Element[lenghtArray];
        this.lastIndex = -1;
        this.indexOf = new HashMap<>();
    }

    public boolean isEmpty() {
        return lastIndex < 0;
    }

    public void insert(int vertice, int priority){
        lastIndex++;
        elements[lastIndex] = new Element(vertice, priority);
        indexOf.put(vertice, lastIndex);
        verifyUp(lastIndex);
    }

    public int extractMinimum(){
        int n = lastIndex;
        Element minimum = elements[0];
        swap(0, lastIndex);
        elements[lastIndex] = null;
        lastIndex--;
        int i = 0;
        while (2*i+1 < n){
            int k = 2*i + 1;
            if (k+1 < n && elements[k+1].priority < elements[k].priority){
                k++;
            }
            if (elements[i].priority < elements[k].priority){
                break;
            }

            swap(i, k);

            i = k;
        }
        return minimum.vertex;
    }

    public void decreaseKey(int vertice, int newPriority){
        int index = indexOf.get(vertice);
        if (newPriority < elements[index].priority){
            elements[index].priority = newPriority;
            verifyUp(index);
        }
    }

    private void verifyUp(int index){
        for (int i = index; i>0 && elements[i].priority < elements[(i-1)/2].priority; i = (i-1)/2){
            swap((i-1)/2, i);
        }
    }

    private void swap(int index, int otherIndex){
        Element tmp = elements[index];
        elements[index] = elements[otherIndex];
        elements[otherIndex] = tmp;

        indexOf.put(elements[index].vertex, index);
        indexOf.put(elements[otherIndex].vertex, otherIndex);
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
