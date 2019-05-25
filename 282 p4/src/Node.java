
public class Node implements  Comparable<Node>{

    private int origin;
    private int destination;
    private int cost;
    private boolean visited;

    public Node(int orig, int destination, int cost) {
        this.origin = orig;
        this.destination = destination;
        this.cost = cost;
        visited = false;
    }

    public int getOrigin() {
        return origin;
    }
    public int getDestination(){
        return destination;

    }

    public int getCost() {
        return cost;
    }
    public void setVisited() {
        this.visited = true;
    }
    public boolean getVisited(){
        return this.visited;
    }
    public boolean hasBeenVisited(){
        this.visited = true;
        return this.visited;
    }

    public int compareTo(Node n){
       return this.getCost()-n.getCost();
    }


    @Override
    public String toString() {
        return "origin=" + origin + "\t" +
                "destination = " + destination + "\t" +
                "cost=" + cost + "\t";
    }
}