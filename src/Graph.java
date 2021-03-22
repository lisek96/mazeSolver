import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Graph {
    Vertice[][] graph;
    int size;
    boolean[][] blockedCells;
    boolean state = false;

    public Graph(int size) {
        this.size = size;
        graph = new Vertice[size][size];
        blockedCells = new boolean[size][size];
    }

    boolean checkBounds(int i, int j) {
        if (i < 0 | j >= size | i >= size | j < 0) return false;
        return true;
    }

    boolean checkIfBlocked(int i, int j) {
        return blockedCells[i][j];
    }

    Vertice getOrCreate(int i, int j) {
        if (!checkBounds(i, j)) return null;
        if (checkIfBlocked(i, j)) return null;
        Vertice v;
        if (graph[i][j] == null) {
            v = new Vertice(i, j);
            graph[i][j] = v;
        } else v = graph[i][j];
        return v;
    }

    public void gogo(){

    }
    public void stop(){

    }


    public void BFS(Maze m) {
        boolean[][] checked = new boolean[size][size];
        Queue<Vertice> queue = new LinkedList<>();
        Queue<Vertice> visited = new LinkedList<>();
        Vertice tmp;
        Vertice start = graph[0][0]; //hardcoded start (not very good)!
        start.parent = start;
        queue.add(start);
        checked[start.i][start.j] = true;
        while (!queue.isEmpty()) {
            while(state) {}
                tmp = queue.poll();
                visited.add(tmp);
                if (tmp.i == size - 1 && tmp.j == size - 1) {
                    m.setVisited(tmp.i, tmp.j);
                    return;
                }
                for (Vertice v : tmp.adjacencyList) {
                    m.setVisited(tmp.i, tmp.j);
                    if (checked[v.i][v.j] == false) {
                        checked[v.i][v.j] = true;
                        queue.add(v);
                        m.setSeen(v.i, v.j);
                        v.parent = tmp;
                    }


            }
        }
    }

    public void DFS() {
        boolean[][] checked = new boolean[size][size];
        List<Vertice> visited = new LinkedList<>();
        Vertice tmp;
        Deque<Vertice> stack = new ArrayDeque<>();
        Vertice start = graph[0][0];//hardcoded start (not very good)!
        start.parent = start;
        stack.push(start);
        checked[start.i][start.j] = true;

        while (!stack.isEmpty()) {
            tmp = stack.pop();
            visited.add(tmp);
            for (Vertice v : tmp.adjacencyList) {
                if (checked[v.i][v.j] == true) continue;
                checked[v.i][v.j] = true;
                stack.push(v);
                v.parent = tmp;
            }
        }
    }

    void blockCell(int i, int j) {
        blockedCells[i][j] = true;
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {


            int size = 10;
            Graph g = new Graph(size);

            Random random = new Random();
            for(int i=0; i<30; i++){
                int r = random.nextInt(size-1)+1;
                int c = random.nextInt(size)+0;
                if(r==0 && c==0) continue;
                if(r==9 && c==9) continue;
                if(r==1 && c==0) continue;
                if(r==0 && c==1) continue;
                if(r==8 && c==9) continue;
                if(r==9 && c==8) continue;
                g.blockCell(r, c);
                //x
            }

            for (int i = 0; i < g.size; i++) {
                for (int j = 0; j < g.size; j++) {
                    if (g.checkIfBlocked(i, j)) continue;
                    Vertice v = g.getOrCreate(i, j);
                    v.addNeighbours(g);
                }
            }

            for (int i = 0; i < g.size; i++) {
                for (int j = 0; j < g.size; j++) {
                    if (g.checkIfBlocked(i, j)) continue;
                    System.out.println(g.graph[i][j] + " " + g.graph[i][j].adjacencyList);
                }
            }
            var maze = new Maze(size, g);

            g.BFS(maze);

            maze.drawPath();

        /*

        this is console version

        for (int i = 0; i < g.size; i++) {
            for (int j = 0; j < g.size; j++) {
                if (g.checkIfBlocked(i, j)) continue;
                System.out.println(g.graph[i][j] + " " + g.graph[i][j].adjacencyList);
            }
        }

        g.DFS();
        System.out.println("DFS: ");
        var ver = g.graph[4][4];
        while(ver.parent!=ver){
            ver=ver.parent;
            System.out.println(ver);
        }

        g.BFS();
        System.out.println("BFS: ");
        var ver1 = g.graph[4][4];
        while(ver1.parent!=ver1){
            ver1=ver1.parent;
            System.out.println(ver1);
        }


 */
    }
}
