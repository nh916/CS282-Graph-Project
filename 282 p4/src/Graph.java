import java.util.*;

public class Graph {

//    im using the position of the array as the vertex and the list inside to hold the neighbors the vertex is adjacent to

    int numberOfNodes;
    private LinkedList<Node>[] vertex;
    private ArrayList<Node> edges;


    public Graph(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;

        vertex = new LinkedList[numberOfNodes];
        edges = new ArrayList<Node>();


        for (int i = 0; i < numberOfNodes; i++) {
            vertex[i] = new LinkedList<>();
        }
    }

    //    was needed but now not really?
    public void addVertex(int index, Node vertexNode) {
        vertex[index].add(vertexNode);
    }

    // using this
    public void addNeighbor(int index, Node neighborNode) {
        vertex[index].add(neighborNode);
    }

    //    was needed but now not really?
    public boolean checkReachable(int checkFrom, int checkto) {

//        for (int i = 0; i < vertex.length; i++) {
//            for (int j = 0; j < vertex[i].size(); j++) {
//                if (vertex[i].get(j).getVertex() == checkto){
//                    return true;
//                }
//            }
//        }

        for (int i = 0; i < vertex[checkFrom].size(); i++) {
            if (vertex[checkFrom].get(i).getOrigin() == checkto) {
                return true;
            }
        }

        return false;
    }


    /*public boolean connected() {
        int checkToSeeIfAnythingPointsToThis;
        boolean returnThis = true;

        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i].isEmpty()) {
                returnThis = false;
                for (int j = 0; j < vertex.length; j++) {
                    for (int k = 0; k < vertex[j].size(); k++) {
                        if (vertex[j].get(k).getVertex() == vertex[i].get(i).getVertex()){
                            returnThis = true;
                        }
                    }
                }
            }
        }
        return returnThis;
    }*/

    //    checks to see if there is any node that has an empty list
    public boolean connected() {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // print
    public void print() {
        for (int i = 0; i < vertex.length; i++) {
            System.out.println("position " + i + vertex[i]);
        }
    }

    // started on it but got stuck and stopped
    public void MST() {
        //puts all edges within an arraylist for access
        for (int i = 0; i < vertex.length; i++) {
            LinkedList<Node> temp = vertex[i];
            for (int j = 0; j < vertex[i].size(); j++) {
                if (!edges.contains(vertex[i].get(j))) {
                    edges.add(vertex[i].get(j));
                }

            }
        }
        if (!connected()) {
            System.out.println("Tree is not connected, cannot run mst");
        } else {

            ArrayList<Node> mstEdges = new ArrayList<Node>();
            Collections.sort(edges);
            DisjointSet nodeSet = new DisjointSet(numberOfNodes + 1);

            for (int i = 0; i < edges.size() && mstEdges.size() < (numberOfNodes - 1); i++) {

                Node curNode = edges.get(i);
                int root1 = nodeSet.find(curNode.getOrigin());
                int root2 = nodeSet.find(curNode.getDestination());

                if (root1 != root2) {
                    mstEdges.add(curNode);


                    nodeSet.union(root1, root2);
                }

            }


            for (int k = 0; k < mstEdges.size(); k++) {
                System.out.println(mstEdges.get(k));
            }

        }


    }

    //Used for dijkstras
    //
    public void dijkstras(int source) {
        int[] distances = new int[numberOfNodes];
        int[] cost = new int[numberOfNodes];
        boolean[] visited = new boolean[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            visited[i] = false;
            distances[i] = -1;
            cost[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < vertex.length; i++) {
            LinkedList<Node> temp = vertex[i];
            for (int j = 0; j < vertex[i].size(); j++) {
                if (vertex[i].contains(j)) {
                    int origin = vertex[i].get(0).getOrigin();
                    int dest = vertex[i].get(j).getDestination();
                }

            }
        }

    }


    /*ublic ArrayList<Node> shortestDist(Node start, Node finish) {
        Integer cost = Integer.MAX_VALUE;
        ArrayList<Node> path = new ArrayList<Node>();

        for (int i = 0; i < vertex.length; i++) {
//            found the starting origin
            if (i == start.getOrigin()) {
                for (int j = 0; j < vertex[i].size(); j++) {
                    if (vertex[i].get(j).getCost() <= cost) {
//                        found a smaller cost
//                        cost = vertex[i].get(j).getCost();
//                        add the path
                        path.add(vertex[i].get(j));
//                        if we got to it then stop
                        if (vertex[i].get(j).getDestination() == finish.getOrigin()){
                            return path;
//                            break;
                        }
//                        shortestDist(vertex[i].get(j), finish);
                    }

                }
            }
        }
        return path;
    }*/





}





