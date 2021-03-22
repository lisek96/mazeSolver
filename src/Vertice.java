import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertice {
    Vertice parent;
    int i;
    int j;
    List<Vertice> adjacencyList = new ArrayList<>();

    public Vertice(int row, int column) {
        this.i = row;
        this.j = column;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
        return i == vertice.i &&
                j == vertice.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    void addNeighbours(Graph g){
       Vertice v1, v2, v3, v4;
       v1=g.getOrCreate(i+1, j);v2=g.getOrCreate(i-1, j);v3=g.getOrCreate(i, j+1); v4=g.getOrCreate(i, j-1);
       for(Vertice v : new Vertice[]{v1,v2,v3,v4}){
           if(v!=null){
               adjacencyList.add(v);
           }
       }
    }

    @Override
    public String toString() {
        return "("+i + "," + j + ")";
    }
}
