import java.util.*;

public class shortest {

    /** Construct an empty */
    public shortest() {
    }

    private static final int NO_PARENT = -1;


    public void dijkstra(double shortest[][], int src,int destination) {

        /*TODO  ADD YOUR CODE IN THIS METHOD TO IMPLEMENT THE DIJKSTRA ALGORITHM */

        int nVertices = shortest[0].length;
        int[] mark = new int[nVertices];
        int current = src;


        // shortestDistances[i] will hold the
        // shortest distance from src to i
        double[] shortestDistances = new double[nVertices];

        // Parent map to store shortest
        // path tree
        Map<Integer, Integer> parents = new HashMap<Integer, Integer>();

        // The starting vertex does not
        // have a parent
        parents.put(src, NO_PARENT);
        mark[src] = 1;

        // Find shortest path for all
        // vertices

        for(int m = 0; m < nVertices; m++){
            if(src != m && shortest[src][m] == 0){
                shortest[src][m] = -1;//Change no paths to -1
            }
            shortestDistances[m] = shortest[src][m];
            if(shortestDistances[m] > 0){
                parents.put(m,src);
            }
            else
                parents.put(m,NO_PARENT);
        }


        for (int i = 1; i < nVertices; i++) { //COMPLETE THIS FOR LOOP

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.

            double max = Integer.MAX_VALUE;
            for(int a = 0; a < nVertices; a++){
                if(mark[a]==0 && (shortest[current][a] > 0 || shortest[src][a] > 0)){
                    if(shortestDistances[a] <= max && shortestDistances[a] > 0){
                        max = shortestDistances[a];
                        current = a;
                    }
                }
                max = Integer.MAX_VALUE;
            }

            mark[current] = 1;

            for(int j = 0; j < nVertices; j++){
                if(mark[j] == 0){
                    if(shortestDistances[j] > shortestDistances[current] + shortest[current][j] && shortest[current][j] != -1){
                        shortestDistances[j] = shortestDistances[current] + shortest[current][j];
                        parents.replace(j,current);
                    }
                    else if(shortestDistances[j] == -1 && shortest[current][j] > 0){
                        shortestDistances[j] = shortestDistances[current] + shortest[current][j];
                        parents.replace(j,current);
                    }
                }
            }
        }
        // print the constructed distance array
        //printSolution(src, shortestDistances, parents, vert);
        printDistance(src, destination, shortestDistances);
    }

    public static void printDistance(int source, int destination, double[] distances){
        System.out.println("The distance from " + source + " to " + destination + " is " + distances[destination]);
    }

    //DO NOT MODIFY THIS METHOD
    public static void printPath(int currentVertex, Map<Integer, Integer> parents, ArrayList<String> vertices) {

        // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT) {
            return;
        }
        printPath(parents.get(currentVertex), parents, vertices);
        System.out.print(vertices.get(currentVertex) + " ");// Important
    }

    //DO NOT MODIFY THIS METHOD
    public void printSolution(int src, int dist[], Map<Integer, Integer> path, ArrayList<String> vertices) {
        System.out.println("All shortest paths from " + vertices.get(src) + " are:");// Important
        for (int i = 0; i < dist.length; i++) {
            System.out.print("A path from " + vertices.get(src) + " to " + vertices.get(i) + ": ");
            printPath(i, path, vertices);
            System.out.println("(cost: " + dist[i] + ")"); // Path cost
        }
    }

}