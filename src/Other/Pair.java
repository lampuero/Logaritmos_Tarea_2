package Other;

import java.io.Serializable;

public class Pair implements Serializable {
    public int vertice;
    public int distance;

    public Pair(int v, int d){
        this.vertice = v;
        this.distance = d;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair otherPair = (Pair) obj;
        return vertice == otherPair.vertice;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + vertice;
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", vertice, distance);
    }
}
