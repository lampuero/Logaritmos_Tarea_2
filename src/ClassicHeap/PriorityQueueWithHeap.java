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

    public void insert(Element element){
        int n = elements.size();
        elements.add(element);
        element.index = n;
        verifyUp(n);
    }

    public int extractMinimum(){
        int n = elements.size() - 1;
        Element minimum = elements.get(0);

        elements.set(0, elements.get(n));
        elements.get(0).index = 0;

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
            elements.get(i).index = i;

            elements.set(k, tmp);
            elements.get(k).index = k;

            i = k;
        }
        return minimum.vertex;
    }

    public void decreaseKey(Element element, int newPriority){
        int index = element.index;
        element.priority = newPriority;
        verifyUp(index);
    }

    private void verifyUp(int index){
        for (int i = index; i>0 && elements.get(i).priority < elements.get((i-1)/2).priority; i = (i-1)/2){
            Element tmp = elements.get(i);
            int j = (i-1)/2;

            elements.set(i, elements.get(j));
            elements.get(i).index = i;

            elements.set(j, tmp);
            elements.get(j).index = j;
        }
    }
}

class Element{
    int vertex;
    int priority;
    int index;

    public Element(int vertex, int priority){
        this.vertex = vertex;
        this.priority = priority;
        this.index = 0;
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
