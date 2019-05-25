import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int vertices;
    public int matrix[][];
    private int visited[][];


    public Graph(int vertex) {
        this.vertices = vertex;
        matrix = new int[vertex][vertex];
        visited = new int[vertex][vertex];

        for (int row = 0; row < visited.length; row++) {
            for (int col = 0; col < visited[row].length; col++) {
                visited[row][col] = -1;
            }
        }


//        for (int row = 0; row < matrix.length; row++) {
//            for (int col = 0; col < matrix[row].length; col++) {
//                visited[row][col] = -1;
//            }
//        }
    }

    public void addEdge(int source, int destination, int weight) {
//add edge
        matrix[source][destination] = weight;
//add back edge for undirected graph
        matrix[destination][source] = weight;
        this.visited[source][destination] = weight;
        this.visited[destination][source] = weight;

    }

    public int getMinimumVertex(boolean[] mst, int[] key) {
        int minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < vertices; i++) {
            if (mst[i] == false && minKey > key[i]) {
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }

    class ResultSet {
        // will store the vertex(parent) from which the current vertex will reached
        int parent;
        // will store the weight for printing the MST weight
        int weight;
    }

    public void primMST() {
        boolean[] mst = new boolean[vertices];
        ResultSet[] resultSet = new ResultSet[vertices];
        int[] key = new int[vertices];
//Initialize all the keys to infinity and
//initialize resultSet for all the vertices
        for (int i = 0; i < vertices; i++) {
            key[i] = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
        }
//start from the vertex 0
        key[0] = 0;
        resultSet[0] = new ResultSet();
        resultSet[0].parent = -1;
//create MST
        for (int i = 0; i < vertices; i++) {
//get the vertex with the minimum key
            int vertex = getMinimumVertex(mst, key);
//include this vertex in MST
            mst[vertex] = true;
//iterate through all the adjacent vertices of above vertex and update the keys
            for (int j = 0; j < vertices; j++) {
//check of the edge
                if (matrix[vertex][j] > 0) {
//check if this vertex 'j' already in mst and
//if no then check if key needs an update or not
                    if (mst[j] == false && matrix[vertex][j] < key[j]) {
//update the key
                        key[j] = matrix[vertex][j];
//update the result set
                        resultSet[j].parent = vertex;
                        resultSet[j].weight = key[j];
                    }
                }
            }
        }
//print mst
        printMST(resultSet);
    }

    /*public void printMST(ResultSet[] resultSet) {
        int total_min_weight = 0;
        System.out.println("Minimum Spanning Tree: ");
        for (int i = 1; i < vertices; i++) {
            System.out.println("Edge: " + i + " - " + resultSet[i].parent +
                    " key: " + resultSet[i].weight);
            total_min_weight += resultSet[i].weight;
        }
        System.out.println("Total minimum key: " + total_min_weight);
    }*/


    public void printMST(ResultSet[] resultSet) {
        int total_min_weight = 0;
        System.out.println("Minimum Spanning Tree: ");
//        System.out.println("The edges used were: ");
        System.out.println(vertices);
        for (int i = 1; i < vertices; i++) {
            System.out.println("Edge: " + i + " - " + resultSet[i].parent +
                    " key: " + resultSet[i].weight);
            total_min_weight += resultSet[i].weight;
        }
        System.out.println("Total minimum key: " + total_min_weight);
    }


    private int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < vertices; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed distance array
    public void printSolution(int dist[], int n, int source) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < vertices; i++)
//            System.out.println(i + " tt " + dist[i]);
            System.out.println("from " + source + " to " + i + " the cost is " + dist[i]);
    }

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    public void dijkstra(int graph[][], int src) {
        int dist[] = new int[vertices]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[vertices];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < vertices - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < vertices; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // print the constructed distance array
        printSolution(dist, vertices, src);
    }


    private Queue<Integer> queue = new LinkedList<Integer>();

    public void bfs(int source) {
        int adjacency_matrix[][] = matrix;
        int number_of_nodes = adjacency_matrix[source].length - 1;

        int[] visited = new int[number_of_nodes + 1];
        int i, element;
        visited[source] = 1;
        queue.add(source);
        while (!queue.isEmpty()) {
            element = queue.remove();
            i = element;
            while (i <= number_of_nodes) {
                if (adjacency_matrix[element][i] == 1 && visited[i] == 0) {
                    queue.add(i);
                    visited[i] = 1;
                }
                i++;
            }
        }
        boolean connected = false;

        for (int vertex = 1; vertex <= number_of_nodes; vertex++) {
            if (visited[vertex] == 1) {
                connected = true;
            } else {
                connected = false;
                break;
            }
        }

        if (connected) {
            System.out.println("The graph is connected");
        } else {
            System.out.println("The graph is disconnected");
        }
    }


    public boolean isFullyConnected() {
        int[][] m = matrix;
        for (int i = 0; i < m.length; i++) //iterate over rows
            for (int j = 0; j < m[i].length; j++) //iterate over columns
                if (i != j && m[i][j] == 0) //if not in main diag. and not connected
                    return false;
        return true;
    }


    public boolean isConnected() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
//                matrix[row][col] = row * col;
//                if (visited[row][col] == -1 && matrix[row][col] == -1){
//                    return false;
//                }
                if (visited[row][col] != -1){
                    return true;
                }
            }
        }
        return false;
    }


}


